package com.example.myapplication;

import static java.lang.String.valueOf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ViewHolder> {

    private ArrayList<Report> reportsList;
    private Context context;
    Issue issue;

    // constructor
    public ReportsAdapter(ArrayList<Report> reportsList, Context context) {
        this.reportsList = reportsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReportsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsAdapter.ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Report report = reportsList.get(position);
        holder.reportIssue.setText(valueOf(report.getIssue()));
        holder.reportDescription.setText(report.getDescription());
        holder.reportImage.setImageBitmap(report.getImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OpenReportActivity.class);
                i.putExtra("ID", holder.getAdapterPosition());
                i.putExtra("issue", reportsList.get(holder.getAdapterPosition()).getIssue());
                i.putExtra("description", reportsList.get(holder.getAdapterPosition()).getDescription());
                i.putExtra("location", reportsList.get(holder.getAdapterPosition()).getLocation());

                context.startActivity(i);
            }

        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return reportsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView reportIssue, reportDescription;
        ImageView reportImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            reportIssue = itemView.findViewById(R.id.report_issue);
            reportDescription = itemView.findViewById(R.id.report_description);
            reportImage = itemView.findViewById(R.id.img_report);
            cardView = itemView.findViewById(R.id.reports_container);
        }
    }
}
