package com.example.ben.bluechat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Ben on 4/5/16.
 */
public class BluetoothChatMessage {
    private String message;
    private boolean ack = false;
    private long timestamp;
    private String destination;
    private int clickcount = 0;


    BluetoothChatMessage(String message) {
        this.message = message;
    }
    BluetoothChatMessage(String message, long timestamp, String destination) {
        this.message = message;
        this.timestamp = timestamp;
        this.destination = destination;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getAck() {
        return this.ack;
    }
    public void setAck(boolean status) {
        this.ack = status;
    }

    public String getDestination() {return this.destination; }

    public int getClickcount() {return this.clickcount;}
    public void incrementClickcount() {this.clickcount++;}

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



}
