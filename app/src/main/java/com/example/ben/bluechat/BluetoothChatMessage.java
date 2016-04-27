package com.example.ben.bluechat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class is used to hold all the data of individual messages.
 */
public class BluetoothChatMessage {
    // Message information
    private String message;
    private boolean ack = false;
    private long timestamp;

    // Click count for each message to know if we should show more info or not in the view
    private int clickcount = 0;

    /**
     * Constructors
     */
    BluetoothChatMessage(String message) {
        this.message = message;
    }
    BluetoothChatMessage(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * Getters for date and time conversion from timestamp
     */
    public String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yy hh:mm");

        String time = sdf.format(new Date(timestamp));

        return time;
    }
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

        String time = sdf.format(new Date(timestamp));

        return time;
    }
    public String getMiliseconTime() {
        return Long.toString(timestamp);
    }

    /**
     * Simple getters and setters
     */
    public String getMessage() {
        return this.message;
    }

    public boolean getAck() {
        return this.ack;
    }
    public void setAck(boolean status) {
        this.ack = status;
    }

    public int getClickcount() {return this.clickcount;}
    public void incrementClickcount() {this.clickcount++;}
}
