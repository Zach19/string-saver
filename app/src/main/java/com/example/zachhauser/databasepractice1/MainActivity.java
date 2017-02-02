package com.example.zachhauser.databasepractice1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    EditText authorEditableET, quoteEditableET;
    TextView authorTV, quoteTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authorEditableET = (EditText) findViewById(R.id.authorEditableET);
        quoteEditableET = (EditText) findViewById(R.id.quoteEditableET);

        authorTV = (TextView) findViewById(R.id.authorTV);
        quoteTV = (TextView) findViewById(R.id.quoteTV);
    }

    public void newQuote(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Quotes quote = new Quotes(authorEditableET.getText().toString(), quoteEditableET.getText().toString());
        dbHandler.addQuote(quote);
    }

    public void lookupAuthor(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Quotes quote = dbHandler.findQuotes(authorEditableET.getText().toString());

        if (quote != null) {
            authorTV.setText(String.valueOf(quote.getName()));
            quoteTV.setText(quote.getQuote());
        }
        else {
            authorTV.setText(getResources().getString(R.string.noMatch));
        }
    }

    public void deleteQuote(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        boolean result = dbHandler.deleteQuotes(authorEditableET.getText().toString());

        if (result) {
            authorTV.setText(getResources().getString(R.string.quoteDeleted));
            quoteTV.setText("");
        }
        else {
            authorTV.setText(getResources().getString(R.string.noMatch));
            quoteTV.setText("");
        }
    }

}

