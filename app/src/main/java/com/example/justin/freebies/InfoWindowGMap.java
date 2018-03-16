package com.example.justin.freebies;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class InfoWindowGMap implements GoogleMap.InfoWindowAdapter{
    private Context context;

    public InfoWindowGMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.map_custom_infowindow, null);

        TextView title_tv = view.findViewById(R.id.title);
        TextView description_tv = view.findViewById(R.id.description);
        ImageView img = view.findViewById(R.id.pic);
        TextView date_tv = view.findViewById(R.id.date);
        TextView location_tv = view.findViewById(R.id.location);

        title_tv.setText(marker.getTitle());
        description_tv.setText(marker.getSnippet());

        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        if(infoWindowData.getImage().contains("http")) {
            Picasso.get().load(infoWindowData.getImage()).resize(300,400).centerCrop().into(img);
        }
        else if(infoWindowData.getImage() != null && !infoWindowData.getImage().isEmpty())
        {
            byte[] decodedByteArray = android.util.Base64.decode(infoWindowData.getImage(), Base64.DEFAULT);
            img.setImageBitmap(BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length));
        }
        else{}

        date_tv.setText(infoWindowData.getDate());
        location_tv.setText(infoWindowData.getLocation());

        return view;
    }
}
