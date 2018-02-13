package com.example.darshan.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class ScoreActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ProgressBar pb = (ProgressBar) findViewById(R.id.pb_load);
        pb.setVisibility(8);
        String country_name = getIntent().getStringExtra("cname");


        TextView tv = (TextView)findViewById(R.id.tv_score);
        String apikey = "0RKpiKRCFQPUYCGiFXA24UvDiRW2";
        CricHere ca = new CricHere();

        ca.setApiKey(apikey);
        ca.setCountryName(country_name);

        try {
            tv.setText(ca.getMatchDetails());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Toast.makeText(this, ca.getLiveMatchStatistic(), Toast.LENGTH_LONG).show();
            while(!ca.getLiveMatchStatistic().equals("No match right now")){

                tv.setText(ca.getLiveMatchStatistic());
            }
        }
        catch (IOException e){
            Toast.makeText(this, "Caught IO Exception", Toast.LENGTH_SHORT).show();
        }


    }

    protected void onDestroy() {
        super.onDestroy();

    }


}
