package com.retardprod.lo10.the_guildmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class QuestPreparationActivity extends Activity {

    private static final String TAG = "QuestPreparation";
    private String questId = PublicData.AVAILABLEQUESTS.getJSONArray(0).getJSONObject(PublicData.SELECTED_QUEST).getString("id");
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;

    public QuestPreparationActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_preparation);
        rb1 = (RadioButton) findViewById(R.id.adv1);
        rb2 = (RadioButton) findViewById(R.id.adv2);
        rb3 = (RadioButton) findViewById(R.id.adv3);
        rb4 = (RadioButton) findViewById(R.id.adv4);
        rb5 = (RadioButton) findViewById(R.id.adv5);
        try {
            rb1.setText(PublicData.CHARACTERS.getJSONObject(0).getString("name")+ " - "+PublicData.CHARACTERS.getJSONObject(0).getString("class"));
            rb2.setText(PublicData.CHARACTERS.getJSONObject(1).getString("name")+ " - "+PublicData.CHARACTERS.getJSONObject(1).getString("class"));
            rb3.setText(PublicData.CHARACTERS.getJSONObject(2).getString("name")+ " - "+PublicData.CHARACTERS.getJSONObject(2).getString("class"));
            rb4.setText(PublicData.CHARACTERS.getJSONObject(3).getString("name")+ " - "+PublicData.CHARACTERS.getJSONObject(3).getString("class"));
            rb5.setText(PublicData.CHARACTERS.getJSONObject(4).getString("name")+ " - "+PublicData.CHARACTERS.getJSONObject(4).getString("class"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClickStart (View v){
        String mCharacters []=null;
        int i=0;
        if (rb1.isChecked()){
            try {
                mCharacters[i]=PublicData.CHARACTERS.getJSONObject(0).getString("id");
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (rb2.isChecked()){
            try {
                mCharacters[i]=PublicData.CHARACTERS.getJSONObject(1).getString("id");
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (rb3.isChecked()){
            try {
                mCharacters[i]=PublicData.CHARACTERS.getJSONObject(2).getString("id");
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (rb4.isChecked()){
            try {
                mCharacters[i]=PublicData.CHARACTERS.getJSONObject(3).getString("id");
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (rb5.isChecked()){
            try {
                mCharacters[i]=PublicData.CHARACTERS.getJSONObject(4).getString("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestParams params = new RequestParams();
        params.put("Characters",mCharacters);
        GuildAPIClient.post("player/"+PublicData.ID+"/quest/"+questId+"/start",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, "onSuccess: "+ response.toString());
                Toast.makeText(QuestPreparationActivity.this, "La quête a bien démarré", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuestPreparationActivity.this,MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
