package com.example.requestapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.requestapp.model.Task;

import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List< Task > mData;

    public RecycleViewAdapter(Context context , List< Task > mDataTask) {
        this.mContext=context;
        this.mData=mDataTask;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        View view;
            view = mInflater.inflate(R.layout.task_detalist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.descryption.setText(mData.get(position).getDescryption());
        if(mData.get(position).getType().contains("Low")){
            holder.image.setImageResource(R.drawable.ic_low_priority);
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorLow));
        }else if(mData.get(position).getType().contains("Medium")){
            holder.image.setImageResource(R.drawable.ic_medium_priority);
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorMedium));
        }else {
            holder.image.setImageResource(R.drawable.ic_high_priority);
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorHigh));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView descryption;

        @SuppressLint("ResourceAsColor")
        public MyViewHolder(View itemView){
            super(itemView);
            descryption=itemView.findViewById(R.id.description_task);
            image=itemView.findViewById(R.id.imgPiority);
        }
    }

}
