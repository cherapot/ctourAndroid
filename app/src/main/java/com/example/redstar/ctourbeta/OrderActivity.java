package com.example.redstar.ctourbeta;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redstar.ctourbeta.dto.CtourResponse;
import com.example.redstar.ctourbeta.dto.JSONParser;
import com.example.redstar.ctourbeta.dto.TourMoreDTO;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by RedStar on 18.06.2016.
 */
public class OrderActivity extends AppCompatActivity {

    Intent intent;
    String id;
    String mTitle, mName, mTel, mCount, mEmail, mDate, mInfo;
    TextView title;
    TextView name;
    TextView tel;
    TextView count;
    TextView email;
    TextView date;
    TextView info;

    Button order;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        intent = getIntent();
        id = intent.getStringExtra("id");
        mTitle = intent.getStringExtra("name");

        title = (TextView) findViewById(R.id.orderTitle);
        title.setText(mTitle);



        order = (Button) findViewById(R.id.orderRequest);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (TextView) findViewById(R.id.orderName);
                tel = (TextView) findViewById(R.id.orderTel);
                count = (TextView) findViewById(R.id.orderCount);
                email = (TextView) findViewById(R.id.orderEmailt);
                date = (TextView) findViewById(R.id.orderDate);
                info = (TextView) findViewById(R.id.orderInfo);

                mName = name.getText().toString();
                mTel = tel.getText().toString();
                mCount = count.getText().toString();
                mEmail = email.getText().toString();
                mDate = date.getText().toString();
                mInfo = info.getText().toString();

                new OrderTourTask().execute();


            }
        });
    }

    private void doPostRequest() throws JSONException, IOException {
        String url = Constant.URL.HOST+Constant.URL.SET_ORDER;

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jData = new JSONObject();

        try {
            jData.put("id_tour", Integer.parseInt(id));
            jData.put("name", mName);
            jData.put("tel",  mTel);
        }catch (JSONException je) {

        }

        RequestBody body = RequestBody.create(JSON, jData.toString());
        Request req = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response res =  client.newCall(req).execute();
        }catch (IOException e) {
        }


    }

    private class OrderTourTask extends AsyncTask<Void, Void, TourMoreDTO> {
        private static final String TAG = "CTour";

        @Override
        protected TourMoreDTO doInBackground(Void... params) {

            try {
                doPostRequest();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(TourMoreDTO tourDTO) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Тур успешно заказан!", Toast.LENGTH_LONG);
            toast.show();
        }
    }



}
