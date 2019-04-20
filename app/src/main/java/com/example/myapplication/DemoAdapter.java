package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyViewHolder>{

    ArrayList<DemoData> dataArrayList;
    int rowIndex=0;
    Context context;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public DemoAdapter(ArrayList<DemoData> dataArrayList,Context context) {
        this.dataArrayList = dataArrayList;

    }

    public DemoAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.dateTextView.setText(dataArrayList.get(position).date);
        if(position==rowIndex)
        {
            holder.dateTextView.setTextColor(context.getResources().getColor(R.color.white));
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.blueLight));

        }
        else
        {
            holder.dateTextView.setTextColor(context.getResources().getColor(R.color.black));
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
                rowIndex=position;
                notifyDataSetChanged();
            }
        });


    }

    public void setData(ArrayList<DemoData> data,Context context){
        this.context=context;
        dataArrayList=data;
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView dateTextView;
    CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        dateTextView=itemView.findViewById(R.id.date_tv);
        cardView=itemView.findViewById(R.id.list_card_view);
    }
}
    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}
