package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<String> issuesList;
    private ArrayList<Integer> issuesIDsList;
    Context context;

    public RecyclerAdapter(ArrayList<String> issuesList, ArrayList<Integer> issuesIDsList,Context context){
        this.issuesList = issuesList;
        this.context = context;
        this.issuesIDsList = issuesIDsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView issueNameText;
        LinearLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            issueNameText = itemView.findViewById(R.id.issueName);
            parentLayout = itemView.findViewById(R.id.singleIssueLayout);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.issues_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = issuesList.get(position);
        int ID = issuesIDsList.get(position);
        holder.issueNameText.setText(name) ;

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReportActivity.class);
                intent.putExtra("issue", issuesList.get(holder.getAdapterPosition()));
                intent.putExtra("issueId", issuesIDsList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return issuesList.size();
    }
}
