package com.retardprod.lo10.the_guildmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "Register";
    private EditText pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pseudo = (EditText) findViewById(R.id.pseudo);
    }

    public void register (View v){
        PublicData.NAME = pseudo.getText().toString();
        RequestParams params = new RequestParams();
        params.put("name",PublicData.NAME);
        params.put("googleId",PublicData.GOOGLE_ID);
        params.put("email",PublicData.MAIL);
        GuildAPIClient.post("player",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, "onSuccess: " + response.toString());
                try {
                    PublicData.ID = response.getString("_id");
                    PublicData.REPUTATION = response.getJSONObject("player").getInt("reputation");
                    PublicData.MONEY = response.getJSONObject("player").getInt("money");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }
}
