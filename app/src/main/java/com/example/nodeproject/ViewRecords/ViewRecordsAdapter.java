package com.example.nodeproject.ViewRecords;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodeproject.R;
import com.example.nodeproject.Model.User;
import com.example.nodeproject.UpdateRecordActivity;

import java.util.List;

public class ViewRecordsAdapter extends RecyclerView.Adapter<ViewRecordsAdapter.ViewRecordsViewHolder> {
    private Context context;
    private List<User> users;

    public ViewRecordsAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public class ViewRecordsViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, edit;

        public ViewRecordsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            edit = itemView.findViewById(R.id.edit);
        }
    }

    @NonNull
    @Override
    public ViewRecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewRecordsViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.layout_records, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRecordsViewHolder holder, int position) {
        final User user = users.get(position);

        holder.email.setText(user.getEmail());
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateRecordActivity.class);
                intent.putExtra("ID", user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
