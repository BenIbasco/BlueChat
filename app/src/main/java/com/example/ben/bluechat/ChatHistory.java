package com.example.ben.bluechat;
/*Created by: Eduardo Pozo
*File Name: ChatsHistory
*Description: This file helps store the chat history between two users by creating an object that holds
* both the device name and the ArrayList string of the device name.  It then converts the ArrayList
* into a JSON strong through the api of gson (to enable go to file, project structure, '+' button
* dependencies, and select the gson library).  The reason behind this is that an SQLite database
* does not inherently store ArrayLists
* Last Update: 04/29/2016
*/

//must set this in project dependencies
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChatHistory {
    public String deviceName;
    ArrayList<String> messageList;
    //used to store array
    Gson gson = new Gson();
    String chatString;

    public ChatHistory(String name, ArrayList<String> chat) {
        deviceName = name;
        if(chat.isEmpty()) {
            messageList.add(0,"The message list was empty");
        } else
            messageList = chat;
    }
    //Empty
    public ChatHistory() {}

    public String getDeviceName(){
        return deviceName;
    }
    public String getMessageList(){
        //Convert to GSON object to, SQLite does not accept ArrayLists, only strings
        chatString = gson.toJson(messageList);
        return chatString;
    }
    //Set the current chat string to an arraylist
    public ArrayList<String> setMessageList(String chat) {
        Type newType = new TypeToken<ArrayList<String>>(){}.getType();
        Gson gson = new Gson();
        messageList = gson.fromJson(chat, newType);
        if(messageList.isEmpty()) {
            messageList.add(0, "I made it here, but the list was empty");
            return messageList;
        }
        return messageList;
    }
    public void setDeviceName(String name){
        deviceName = name;
    }
}