package com.retardprod.lo10.the_guildmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

public class QuestActivity extends Activity {

    private Button Q1;
    private Button Q2;
    private Button Q3;
    private Button Q4;
    private Button Q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        Q1 = (Button) findViewById(R.id.q1);
        Q2 = (Button) findViewById(R.id.q2);
        Q3 = (Button) findViewById(R.id.q3);
        Q4 = (Button) findViewById(R.id.q4);
        Q5 = (Button) findViewById(R.id.q5);
        try {
            Q1.setText(PublicData.AVAILABLEQUESTS.getJSONObject(0).toString());
            Q2.setText(PublicData.AVAILABLEQUESTS.getJSONObject(1).toString());
            Q3.setText(PublicData.AVAILABLEQUESTS.getJSONObject(2).toString());
            Q4.setText(PublicData.AVAILABLEQUESTS.getJSONObject(3).toString());
            Q5.setText(PublicData.AVAILABLEQUESTS.getJSONObject(4).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClickQ1 (View v){
        PublicData.SELECTED_QUEST = 0;
        Intent intent = new Intent(this,QuestPreparationActivity.class);
        startActivity(intent);
    }

    public void onClickQ2 (View v){
        PublicData.SELECTED_QUEST = 1;
        Intent intent = new Intent(this,QuestPreparationActivity.class);
        startActivity(intent);
    }

    public void onClickQ3 (View v){
        PublicData.SELECTED_QUEST = 2;
        Intent intent = new Intent(this,QuestPreparationActivity.class);
        startActivity(intent);
    }

    public void onClickQ4 (View v){
        PublicData.SELECTED_QUEST = 3;
        Intent intent = new Intent(this,QuestPreparationActivity.class);
        startActivity(intent);
    }

    public void onClickQ5 (View v){
        PublicData.SELECTED_QUEST = 4;
        Intent intent = new Intent(this,QuestPreparationActivity.class);
        startActivity(intent);
    }

}
