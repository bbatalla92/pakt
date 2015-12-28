package btech.pakt;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Brennan on 12/25/2015.
 */
public class ConversationClass {


    public String user1;
    public String user2;
    public String cTimeStamp;
    public Map<String,SingleMessage> messages;

    //public String messages;


    public ConversationClass(){}

    public String getSender() {
        return user1;
    }

    public void setSender(String sender) {
        this.user1 = sender;
    }

    public String getReciever() {
        return user2;
    }

    public void setReciever(String reciever) {
        this.user2 = reciever;
    }

    public String getTimestamp() {
        return cTimeStamp;
    }

    public void setTimestamp(String timestamp) {
        this.cTimeStamp = timestamp;
    }

    public ArrayList<SingleMessage> getMessages() {
        ArrayList<SingleMessage> messagesArray = new ArrayList<>();

        for (Map.Entry<String, SingleMessage> entry : messages.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            messagesArray.add(entry.getValue());
        }

        return messagesArray;
    }

    public void setMessages(Map<String,SingleMessage> messages) {
        this.messages = messages;
    }
}


