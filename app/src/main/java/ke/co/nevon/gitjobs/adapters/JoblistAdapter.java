package ke.co.nevon.gitjobs.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ke.co.nevon.gitjobs.R;
import ke.co.nevon.gitjobs.models.JobObject;

public class JoblistAdapter extends RecyclerView.Adapter<JoblistAdapter.MyHolder> {
    List<JobObject> jobObjectList = new ArrayList<>();
    private static final String TAG = "JoblistAdapter";

    public JoblistAdapter(List<JobObject> jobObjectList) {
        this.jobObjectList = jobObjectList;
    }

    public void setJobObjectList(List<JobObject> jobObjectList) {
        this.jobObjectList = jobObjectList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        JobObject currentJob = jobObjectList.get(position);
        Log.d(TAG, "onBindViewHolder: " + currentJob.getCompany_logo());
        Picasso.get().load(currentJob.getCompany_logo())
                .placeholder(R.drawable.cyrus)
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageView);
        Log.d(TAG, "onBindViewHolder: " + currentJob.getTitle());

        holder.title.setText(currentJob.getTitle());
        holder.type.setText(Html.fromHtml(currentJob.getType()));
        holder.desc.setText(Html.fromHtml(currentJob.getDescription()));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + jobObjectList.size());
        return jobObjectList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, type, desc;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
