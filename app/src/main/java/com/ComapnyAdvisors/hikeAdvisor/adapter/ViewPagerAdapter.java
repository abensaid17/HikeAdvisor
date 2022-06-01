package com.ComapnyAdvisors.hikeAdvisor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ComapnyAdvisors.hikeAdvisor.R;
import com.ComapnyAdvisors.hikeAdvisor.model.Images;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ImageSlider> {
    private List<Images> imagesList;

    public ViewPagerAdapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @NotNull
    @Override
    public ImageSlider onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_pager_row,parent,false);
        return new ImageSlider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImageSlider holder, int position) {
        Picasso.get()
                .load(imagesList.get(position).getUrl())
                .fit()
                .placeholder(android.R.drawable.stat_notify_error)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ImageSlider extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public ImageSlider(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.view_pager_imageview);
        }
    }
}
