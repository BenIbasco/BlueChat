package com.example.ben.bluechat;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom ArrayAdapter to accommodate BluetoothChatMessage and handle correct representation in views
 */
public class BluetoothArrayAdapter extends ArrayAdapter<BluetoothChatMessage> {
    BluetoothChatFragment fragment;

    private static class ViewHolder {
        TextView itemView;
        Button retransmit;
    }

    /**
     * Constructor.
     *
     * @param context The UI Activity Context
     * @param textViewResourceId UI layout to be used for individual messages
     * @param items ArrayList to store BlueChatMessages
     * @param fragment The UI fragment that is instantiating this ArrayAdapter
     */
    public BluetoothArrayAdapter(Context context, int textViewResourceId,
                                 ArrayList<BluetoothChatMessage> items,
                                 BluetoothChatFragment fragment) {
        super(context, textViewResourceId, items);
        this.fragment = fragment;
    }

    /**
     * Method to describe the translation between the data item and the View to display.
     * This method is called for each item in the list you pass to your adapter and is initially
     * called when the adapter is set.
     * @param position Position of the current item in the ArrayList
     * @param convertView Since ArrayAdapters use View cycling, this is the old view to use.
     * @param parent Is the parent UI's view so each message view can inflate into that for
     *               proper layout parameters.
     * @return convertView
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        // Inflate layout for item view.
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.message, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.messageText);
            viewHolder.retransmit = (Button) convertView.findViewById(R.id.retransmit);

            // setTag is used to mark this view in the UI hierarchy
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get current BluetoothChatMessage object
        final BluetoothChatMessage item = getItem(position);

        // Onclick listener for retransmit button
        viewHolder.retransmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retransmitMessage(position);
            }
        });

        // Onclick listener for expanding message to fit all message information and reveal retransmission button if needed
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.incrementClickcount();
                if (item.getMessage().startsWith("Me: ")) { // Is the message destination the local device or the paired device
                    if ((item.getClickcount() & 1) == 1) {  // Is this a odd numbered click
                        if (item.getAck()) {                // Do I have to retransmit
                            viewHolder.itemView.setText(String.format(item.getDateTime() +
                                    "\nMessage successfully received" + "\n" + item.getMessage()));
                        } else {
                            viewHolder.itemView.setText(String.format(item.getDateTime() + " " + item.getMessage()));
                            viewHolder.retransmit.setVisibility(View.VISIBLE);
                        }
                    } else {
                        viewHolder.itemView.setText(String.format(item.getTime() + " " + item.getMessage()));
                        viewHolder.retransmit.setVisibility(View.INVISIBLE);
                    }
                } else { // If the message destination is the paired device, then there is no need to show ack information
                    viewHolder.retransmit.setVisibility(View.INVISIBLE);
                    if ((item.getClickcount() & 1) == 1) { // Is this a odd numbered click
                        viewHolder.itemView.setText(String.format(item.getDateTime() + " " + item.getMessage()));
                    } else {
                        viewHolder.itemView.setText(String.format(item.getTime() + " " + item.getMessage()));
                    }
                }
            }
        });

        if (item!= null) {
            // Set the text for text view to hold the timestamp and message string
            viewHolder.itemView.setText(String.format(item.getTime() + " " + item.getMessage()));

            // Set BluetoothChatMessage object text color according to its ack instance var
            int textColor = item.getAck() ? ContextCompat.getColor(getContext(), R.color.darkgreen) : Color.GRAY;
            viewHolder.itemView.setTextColor(textColor);
        }
        return convertView;
    }

    /**
     * Method to find a specific message string in the adapter and returns the first unacknowledged
     * message match.
     * @param toFind
     * @throws Exception - An exception is thrown if a match is not found.
     */
    public BluetoothChatMessage findMessage(String toFind) throws Exception {
        for (int i = 0; i < getCount(); i++) {
            BluetoothChatMessage temp = getItem(i);
            // Checking for a match on the message and if the message has received an acknowledgement
            if (temp.getMessage().equals(toFind) && !temp.getAck()) {
                return temp;
            }
        }
        throw new Exception("Message not found");
    }

    /**
     * Method to retransmit a message by removing the message from the list and sending it again.
     * @param position Position in the list of the message to be retransmitted
     */
    private void retransmitMessage(final int position) {
        BluetoothChatMessage toRetransmit = getItem(position);

        remove(toRetransmit);
        notifyDataSetChanged();

        fragment.sendMessage(toRetransmit.getMessage().substring(5));
    }

    /**
     * Method to support database saving by returning a list of strings representing the messages
     */
    public ArrayList<String> getMessages() {
        ArrayList<String> listOfMessageStrings = new ArrayList<String>();

        for (int i = 0; i < getCount(); i++) {
            BluetoothChatMessage temp = getItem(i);
            listOfMessageStrings.add(String.valueOf(temp.getMiliseconTime() + temp.getMessage()));
        }

        return listOfMessageStrings;
    }
}
