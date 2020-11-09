package com.example.requestapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requestapp.R;
import com.example.requestapp.model.Task;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List< Task > mData;
    private  OnItemClickListener mitemClickListener;

    public RecycleViewAdapter(Context context , List< Task > mDataTask,OnItemClickListener onItemClickListener) {
        this.mContext=context;
        this.mData=mDataTask;
        mitemClickListener=onItemClickListener;
    }
    public RecycleViewAdapter(Context context , List< Task > mDataTask) {
        this.mContext=context;
        this.mData=mDataTask;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        View  view = mInflater.inflate(R.layout.task_detalist,parent,false);
        return new MyViewHolder(view,mitemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.descryption.setText(mData.get(position).getDescryption());
        if(mData.get(position).getType().contains("Low")){
            holder.image.setImageResource(R.drawable.ic_low_priority);
            holder.itemView.setBackground(holder.itemView.getResources().getDrawable(R.drawable.gradient_backgrund_item_low));
        }else if(mData.get(position).getType().contains("Medium")){
            holder.image.setImageResource(R.drawable.ic_medium_priority);
            holder.itemView.setBackground(holder.itemView.getResources().getDrawable(R.drawable.gradient_background_item_medium));
        }else {
            holder.image.setImageResource(R.drawable.ic_high_priority);
            holder.itemView.setBackground(holder.itemView.getResources().getDrawable(R.drawable.gradient_background_item_high));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView descryption;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView,OnItemClickListener onItemClickListener){
            super(itemView);
            descryption=itemView.findViewById(R.id.description_task);
            image=itemView.findViewById(R.id.imgPiority);
            itemView.setOnClickListener(this);
            this.onItemClickListener=onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition(),image.getDrawable().getCurrent()+"",descryption.getText().toString());
        }
    }

}