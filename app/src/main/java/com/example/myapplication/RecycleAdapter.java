package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private ArrayList<String> recyclePointsList;
    Context context;

    public RecycleAdapter(ArrayList<String> recyclePointsList, Context context){
        this.recyclePointsList = recyclePointsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_points_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String address = recyclePointsList.get(position);
        holder.recylePointAddress.setText(address) ;

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mapAddress = address;
                mapAddress = mapAddress.replace(" ", "+");

                try {
                    mapAddress = "geo:0,0?q=" + mapAddress;
                    Uri location = Uri.parse(mapAddress);
                    Intent i = new Intent(Intent.ACTION_VIEW, location);
                    context.startActivity(i);
                }
                catch (Exception e) {
                    Toast.makeText(context,"Failed to open Maps", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclePointsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView recylePointAddress;
        LinearLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recylePointAddress = itemView.findViewById(R.id.address);
            parentLayout = itemView.findViewById(R.id.recyclePointLayout);
        }
    }


}
