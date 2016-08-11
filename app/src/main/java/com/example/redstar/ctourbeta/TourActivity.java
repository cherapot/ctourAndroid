package com.example.redstar.ctourbeta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redstar.ctourbeta.dto.CtourResponse;
import com.example.redstar.ctourbeta.dto.JSONParser;
import com.example.redstar.ctourbeta.dto.TourDTO;
import com.example.redstar.ctourbeta.dto.TourMoreDTO;
import com.example.redstar.ctourbeta.dto.ToursDTO;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TourActivity extends AppCompatActivity {

    TextView textView;
    TextView name;
    TextView org;
    TextView tel;
    TextView addr;
    TextView info;
    TextView site;
    TextView date;
    ImageView img;
    String id;
    Button order;

    Intent intent;

    Bitmap bitmap;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        textView = (TextView) findViewById(R.id.tourid);
        name = (TextView) findViewById(R.id.moreName);
        org = (TextView) findViewById(R.id.moreOrg);
        tel = (TextView) findViewById(R.id.moreTel);
        addr = (TextView) findViewById(R.id.moreAddr);
        site = (TextView) findViewById(R.id.moreSite);
        info = (TextView) findViewById(R.id.moreInfo);
        date = (TextView) findViewById(R.id.moreDate);
        img = (ImageView) findViewById(R.id.moreImage);

        order = (Button) findViewById(R.id.tourOrder);

        intent = getIntent();

        id = intent.getStringExtra("id");
        textView.setText(id);


        new GetMoreTourTask().execute();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(), OrderActivity.class);
                intent2.putExtra("id", id);
                intent2.putExtra("name", name.getText());
                v.getContext().startActivity(intent2);
            }
        });
    }



    private class GetMoreTourTask extends AsyncTask<Void, Void, TourMoreDTO> {
        private static final String TAG = "CTour";

        @Override
        protected TourMoreDTO doInBackground(Void... params) {
            try {
                JSONParser jp = new JSONParser();

                String result = new CtourResponse()
                        .getUrlString(Constant.URL.HOST + Constant.URL.GET_MORE + id);


                TourMoreDTO tmd = jp.fromJSON(result, TourMoreDTO.class);

                Log.i(TAG, "Fetched contents of URL: " + result);
                return tmd;

            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
            return null;
        }

        @Override
        protected void onPostExecute(TourMoreDTO tourDTO) {
            TourMoreDTO data = tourDTO;

            name.setText(data.getName());
            org.setText(data.getOrg());
            info.setText(data.getInfo());
            addr.setText(data.getAddress());
            tel.setText(data.getTel());
            site.setText( data.getSite());
            date.setText(data.getDate());

            String uri = Constant.URL.IMAGE + data.getImage();
            new LoadImage().execute(uri);
        }
    }



    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TourActivity.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                img.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(TourActivity.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
