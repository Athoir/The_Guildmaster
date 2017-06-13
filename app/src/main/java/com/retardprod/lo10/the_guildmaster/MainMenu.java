package com.retardprod.lo10.the_guildmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainMenu extends Activity {

    private static final String TAG = "MainMenu";
    private TextView name;
    private TextView reput;
    private TextView money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        name = (TextView) findViewById(R.id.MMname);
        reput = (TextView) findViewById(R.id.MMreput);
        money = (TextView) findViewById(R.id.MMmoney);
        name.setText(PublicData.NAME);
        String reputation = String.valueOf(PublicData.REPUTATION);
        String treasury = String.valueOf(PublicData.MONEY);
        reput.setText("Réputation:"+reputation);
        money.setText("Argent: "+treasury);

        GuildAPIClient.get("player/"+ PublicData.ID+"/character",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d(TAG, "onSuccess: "+ response.toString());
                PublicData.CHARACTERS = response;
            }
        });

        GuildAPIClient.get("player/"+ PublicData.ID+"/quest",null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, "onSuccess: "+ response.toString());
                try {
                    PublicData.AVAILABLEQUESTS = response.getJSONObject("availableQuests").getJSONArray("quests");
                    PublicData.CURRENTQUESTS = response.getJSONArray("currentQuests");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void membersClick (View v){
        Intent intent = new Intent(this, MembersActivity.class);
        startActivity(intent);
    }

    public void questsClick (View v){
        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }

    public void currentQuestsClick (View v){
        Intent intent = new Intent(this, CurrentQuestActivity.class);
        startActivity(intent);
    }
}
