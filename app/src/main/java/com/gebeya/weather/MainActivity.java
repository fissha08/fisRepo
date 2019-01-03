package com.gebeya.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.gebeya.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<Weather> list;
    double longtiude, latitude;
    int temp;
    String name;
    boolean loadFinished = false;
    ListView listView;
    CustemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        listPopulator();
        while (!loadFinished) {

        }

         listView = findViewById(R.id.list_view);

         adapter = new CustemAdapter(this, list);

        listView.setAdapter(adapter);

    }



    public void listPopulator() {
        // Create the client
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(Util.URI).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Loading Failed", Toast.LENGTH_SHORT)
                                .show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {


                String file = response.body().string();

                try {
                    JSONObject root = new JSONObject(file);
                    JSONArray lists = root.getJSONArray("list");
                    for (int i = 0; i < lists.length(); i++) {
                        JSONObject object = lists.getJSONObject(i);//to get root object form json
                        JSONObject coordinate = object.getJSONObject("coord");

                        longtiude = coordinate.getDouble("lon");

                        latitude = coordinate.getDouble("lat");

                        JSONObject main = object.getJSONObject("main");
                        temp = (int) main.getDouble("temp");

                        name = object.getString("name");


                        list.add(new Weather(longtiude, latitude, name, temp));

                    }
                    loadFinished = true;



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
