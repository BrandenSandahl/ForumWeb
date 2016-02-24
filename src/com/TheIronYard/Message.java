package com.TheIronYard;

/**
 * Created by branden on 2/24/16 at 11:11.
 */
public class Message {

    int id, replyId;
    String author, text;


    public Message(int id, int replyId, String author, String text) {
        this.id = id;
        this.replyId = replyId;
        this.author = author;
        this.text = text;
    }
}