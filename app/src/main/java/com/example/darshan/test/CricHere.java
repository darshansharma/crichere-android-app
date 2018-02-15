package com.example.darshan.test;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import java.io.IOException;

/**
 * Created by darshan on 2/12/18.
 */

public class CricHere extends  AppCompatActivity{
    //LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    String apikey;
    String unique_id;
    String cname;
    String doc = "default";
    String res = "default";

    /*public void CricHere(){
        ProgressBar lpb = (ProgressBar) LayoutInflater.from(this).inflate(R.layout.activity_score, null);

    }*/
    public String getUniqueId(){
        return unique_id;
    }

    public void setJSONData(String data){   doc = data;     }
    public void setResult(String result){   res = result;   }


    class MatchDetailsTask extends AsyncTask<Void,String,String> {
        String doc1;
        protected String doInBackground(Void... arg0) {
            try {
                doc1 = Jsoup.connect("http://cricapi.com/api/matchCalendar?apikey=" + apikey).header("Accept", "text/javascript").ignoreContentType(true).execute().body().toString();
                setJSONData(doc1);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("ErrorDuringFetching", e.getMessage());
            }
            return doc1;
        }
    }

    class LiveScoreTask extends AsyncTask<Void,String,String> {
        String doc2;

        protected String doInBackground(Void... arg0) {
            try {
                doc2=Jsoup.connect("http://cricapi.com/api/cricketScore?apikey=" + apikey + "&unique_id=" + unique_id).header("Accept", "text/javascript").ignoreContentType(true).execute().body();
                setResult(doc2);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MsgIMP*", e.getMessage());
            }

            return doc2;
        }
    }

    public void setApiKey(String apikey) {
        this.apikey = apikey;
    }

    public int getMin(int x, int y){
        if(x > y){
            return y;
        }
        else{
            return x;
        }
    }
    public void setCountryName(String x){
        cname = x;
    }

    String getCountryName(){
        return cname;
    }

    public String parseJSON(String data){
        StringBuilder sb_temp = new StringBuilder();
        int rindex=0;int k=0;
        if(data.contains("score")){
            rindex = data.indexOf("score");
            rindex += 8;
        }else{
            Log.e("Error: ", "JSON data changed by cricapi");

        }
        for(int i=rindex; i<data.indexOf(",", rindex)-1; i++ ){
            sb_temp.append(data.charAt(i));
        }

        return sb_temp.toString();
    }

    @SuppressLint("WrongConstant")
    public String getMatchDetails() throws IOException {

        new MatchDetailsTask().execute();
        while(doc.equals("default")){
            
        }

        cname = getCountryName(); //Write your country name here

        String cname1 = cname + " v"; //System.out.println(cname1);  // 1st possiblity of your country in JSON data
        String cname2 = "v "+ cname; //System.out.println(cname2);  // 2nd possiblity of your country name in JSON data
        String matchdetail;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        char cname_array[] = new char[10000];
        int cindex; ////set country position from json in local variable
        int cindex1; // cindex 1 for cname1
        int cindex2; // cindex 2 for cname2
        int tempIndex;

        if (doc.toLowerCase().contains(cname1.toLowerCase())) {
            cindex1 = doc.indexOf(cname1);

        }else{cindex1=-99;}//System.out.println("CINDEX1 "+cindex1);
        if (doc.toLowerCase().contains(cname2.toLowerCase())) {
            cindex2 = doc.indexOf(cname2);

        }else{cindex2=-99;}//System.out.println("CINDEX2 "+cindex2);
        if (cindex1==-99 && cindex2!=-99) {
            cindex = cindex2;
            tempIndex = doc.indexOf("name", cindex - 25);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;
        } else if(cindex2==-99 && cindex1!=-99) {
            cindex = cindex1;
            tempIndex = doc.indexOf("name", cindex - 25);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;

        }else{
            cindex = getMin(cindex1, cindex2);
            tempIndex = doc.indexOf("name", cindex - 25);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;
        }
        for (int i = cindex; i < doc.indexOf("}", cindex); i++) {
            sb.append(doc.charAt(i));
        }

        matchdetail = sb.toString();
        matchdetail = matchdetail.replaceAll("\"", "");
        //System.out.println(matchdetail);
        cindex = cindex - 70;
        for (int i = doc.indexOf("unique_id", cindex) + 11; i < doc.indexOf("name", cindex); i++) {
            sb2.append(doc.charAt(i));
        }
        unique_id = sb2.toString();
        unique_id = unique_id.replace("\"", "");
        unique_id = unique_id.replace(",", "");
        //System.out.println("Match Unique ID : " + unique_id);
        return matchdetail;
    }


    @SuppressLint("WrongConstant")
    public String getLiveMatchStatistic() throws IOException {

        String stat;
        String score;
        System.out.println(unique_id);
        if (unique_id.equalsIgnoreCase("will generate 1-2 days before match")) {
            //System.out.println("SORRY MATCH HAS NOT STARTED YET");
            stat = "No match right now";
        } else {
            new LiveScoreTask().execute();
            while(res.equals("default")){

            }
            stat = res;
            stat = parseJSON(stat);
        }
        return stat;
    }



}
