package com.nuevo.testgallary;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Pojo> myList;
    LayoutInflater inflater;
    private Context context;
    OnItemClickListener listener;

    public RecyclerViewAdapter(Context context, OnItemClickListener listener, List<Pojo> myList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
        this.myList = myList;
    }

    public RecyclerViewAdapter(MainDetailsActivity context, OnItemClickListener id, ItemDetailResponse body) {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardlayout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pojo selectedItem = myList.get(position);
        holder.setData(selectedItem, position);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        public void setData(Pojo selectedProduct, int position) {
            textView.setText(selectedProduct.getTitle());
            Picasso.with(context).load(selectedProduct.getThumbnailUrl()).into(imageView);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(myList.get(getAdapterPosition()).getId());
        }
    }
}

interface OnItemClickListener {
    void onItemClick(Integer id);
}
