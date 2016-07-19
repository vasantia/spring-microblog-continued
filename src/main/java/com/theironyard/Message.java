package com.theironyard;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Created by vasantia on 7/19/16.
 */

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
