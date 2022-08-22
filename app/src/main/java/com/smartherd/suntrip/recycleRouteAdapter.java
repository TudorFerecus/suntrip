package com.smartherd.suntrip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class recycleRouteAdapter extends RecyclerView.Adapter<recycleRouteAdapter.MyViewHolder> {

    private ArrayList<RouteItem> itemList;

    public recycleRouteAdapter(ArrayList<RouteItem> itemList_)
    {
        itemList = itemList_;
    }

    public void removeItem(int position)
    {
        itemList.remove(position);
        notifyItemChanged(position);
        notifyItemRangeChanged(position, itemList.size());
    }

    public void addItem(int position, RouteItem landscape){
        itemList.add(position, landscape);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, itemList.size());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titleTxt;
        private TextView contentTxt;
        private ImageView imageView;
        private View view;


        public MyViewHolder(final View view_)
        {
            super(view_);
            view = view_;
            titleTxt = view.findViewById(R.id.tvTitle);
            contentTxt = view.findViewById(R.id.tvContent);
            imageView = view.findViewById(R.id.ivImage);
        }

    }



    @NonNull
    @Override
    public recycleRouteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rotues_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleRouteAdapter.MyViewHolder holder, int position) {
        String title = itemList.get(position).getTitle();
        String content = itemList.get(position).getContent();
        holder.titleTxt.setText(title);
        holder.contentTxt.setText(content);
        Glide.with(holder.view)
                .load(itemList.get(position).getImageCdn())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
