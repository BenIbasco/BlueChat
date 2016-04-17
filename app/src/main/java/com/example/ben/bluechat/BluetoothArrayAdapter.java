package com.example.ben.bluechat;

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
 * Created by Ben on 4/5/16.
 */
public class BluetoothArrayAdapter extends ArrayAdapter<BluetoothChatMessage> {
    BluetoothChatFragment fragment;

    private static class ViewHolder {
        TextView itemView;
        Button retransmit;
    }

    public BluetoothArrayAdapter(Context context, int textViewResourceId,
                                 ArrayList<BluetoothChatMessage> items,
                                 BluetoothChatFragment fragment) {
        super(context, textViewResourceId, items);
        this.fragment = fragment;
    }

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
                                    "\nReceived by " + item.getDestination() + "\n" + item.getMessage()));
                        } else {
                            viewHolder.itemView.setText(String.format(item.getDateTime() + " " + item.getMessage()));
                            viewHolder.retransmit.setVisibility(View.VISIBLE);
                        }
                    } else {
                        viewHolder.itemView.setText(String.format(item.getTime() + " " + item.getMessage()));
                        viewHolder.retransmit.setVisibility(View.INVISIBLE);
                    }
                } else { // If the message destination is the paired device, then there is no need to show ack information
                    if ((item.getClickcount() & 1) == 1) { // Is this a odd numbered click
                        viewHolder.itemView.setText(String.format(item.getDateTime() + " " + item.getMessage()));
                    } else {
                        viewHolder.itemView.setText(String.format(item.getTime() + " " + item.getMessage()));
                    }
                }
            }
        });

        if (item!= null) {
            viewHolder.itemView.setText(String.format(item.getTime() + " " + item.getMessage()));

            // Set BluetoothChatMessage object text color according to its ack instance var
            int textColor = item.getAck() ? ContextCompat.getColor(getContext(), R.color.darkgreen) : Color.GRAY;
            viewHolder.itemView.setTextColor(textColor);
        }
        return convertView;
    }

    public BluetoothChatMessage findMessage(String toFind) throws Exception {
        for (int i = 0; i < getCount(); i++) {
            BluetoothChatMessage temp = getItem(i);
            if (temp.getMessage().equals(toFind)) {
                return temp;
            }
        }
        throw new Exception("Message not found");
    }

    private void retransmitMessage(final int position) {
        BluetoothChatMessage toRetransmit = getItem(position);

        remove(toRetransmit);
        notifyDataSetChanged();

        fragment.sendMessage(toRetransmit.getMessage().substring(5));
    }


}
