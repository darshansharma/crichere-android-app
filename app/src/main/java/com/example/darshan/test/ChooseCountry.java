package com.example.darshan.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class ChooseCountry extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_australia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "Australia");
                startActivity(i);
            }
        });

        findViewById(R.id.btn_england).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "England");
                startActivity(i);
            }
        });

        findViewById(R.id.btn_newzealand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "New Zealand");
                startActivity(i);
            }
        });

        findViewById(R.id.btn_india).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "India");
                startActivity(i);
            }
        });

        findViewById(R.id.btn_pakistan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "Pakistan");
                startActivity(i);
            }
        });

        findViewById(R.id.btn_westindies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "West Indies");
                startActivity(i);
            }
        });

        findViewById(R.id.btn_southafrica).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "South Africa");
                startActivity(i);
            }
        });

        findViewById(R.id.btn_srilanka).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChooseCountry.this, ScoreActivity.class);
                i.putExtra("cname", "Sri Lanka");
                startActivity(i);
            }
        });





        /*try {
            Toast.makeText(this, ca.getMatchDetails(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Toast.makeText(this, ca.getLiveMatchStatistic(), Toast.LENGTH_LONG).show();
        }
        catch (IOException e){
            Toast.makeText(this, "Caught IO Exception", Toast.LENGTH_SHORT).show();
        }*/



    }

}
