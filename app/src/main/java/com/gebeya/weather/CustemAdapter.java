package com.gebeya.weather;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class CustemAdapter extends ArrayAdapter<Weather> {
    public Weather weather;
    public CustemAdapter(Context context, List<Weather> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          weather = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_list_item, parent, false);
        }

        TextView cityName = convertView.findViewById(R.id.city);
        TextView temprature = convertView.findViewById(R.id.temp);
        Button location = convertView.findViewById(R.id.cord);

        cityName.setText(weather.getCityName());
        temprature.setText("" + weather.getTemprature());

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri city = Uri.parse("geo:"+weather.getLatitude()+","+weather.getLongtiude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, city);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(getContext(),mapIntent,null);// may cause error
            }
        });


        return convertView;
    }
}
