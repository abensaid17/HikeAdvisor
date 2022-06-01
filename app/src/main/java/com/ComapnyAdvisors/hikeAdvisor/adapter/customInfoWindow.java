package com.ComapnyAdvisors.hikeAdvisor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.ComapnyAdvisors.hikeAdvisor.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.jetbrains.annotations.NotNull;

public class customInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private View view;
    private LayoutInflater layoutInflater;

    public customInfoWindow (Context context){
       this.context = context;
       this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       view = layoutInflater.inflate(R.layout.custom_info_window,null);
    }

    @Override
    public View getInfoContents(@NonNull @NotNull Marker marker) {
        return null;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View getInfoWindow(@NonNull @NotNull Marker marker) {
        TextView parkName = view.findViewById(R.id.info_title);
        TextView parkState = view.findViewById(R.id.info_state);

        parkName.setText(marker.getTitle());
        parkState.setText(marker.getSnippet());
        return view;
    }
}
