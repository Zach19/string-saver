package com.example.zachhauser.databasepractice1;

import static android.R.attr.id;

/**
 * Created by zachhauser on 2016-12-22.
 */

public class Quotes {

    private int id;
    private String author;
    private String quote;

    public Quotes() {
    }

    public Quotes(int id, String name, String quote) {
        this.id = id;
        this.author = name;
        this.quote = quote;
    }

    public Quotes(String name, String quote) {
        this.author = name;
        this.quote = quote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.author = name;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.author;
    }

    public String getQuote() {
        return this.quote;
    }

}
