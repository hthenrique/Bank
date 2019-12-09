package com.example.bank.ui.extract;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.Model.ExtractModel;
import com.example.bank.R;

import java.util.List;

public class ExtractListAdapter extends RecyclerView.Adapter<ExtractListAdapter.ViewHolder> {

    private List<ExtractModel> mExtract;

    public ExtractListAdapter(List<ExtractModel> extract){
        setList(extract);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.item_extract, parent, false);
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtractListAdapter.ViewHolder holder, int position) {
        ExtractModel extract = mExtract.get(position);
        holder.id_transfer.setText(extract.id);
        holder.id_user_to.setText(extract.id_to);
        holder.id_user_from.setText(extract.id_from);
        holder.value.setText(extract.value);
        holder.date.setText(extract.data);
    }

    void replaceData(List<ExtractModel> extract){
        setList(extract);
        notifyDataSetChanged();
    }

    private void setList(List<ExtractModel> extract) {
        mExtract = extract;
    }

    @Override
    public int getItemCount() {
        return mExtract.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView id_transfer;
        TextView id_user_to;
        TextView id_user_from;
        TextView value;
        TextView date;
        View view;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_transfer = itemView.findViewById(R.id.idTransfer);
            id_user_to = itemView.findViewById(R.id.idTo);
            id_user_from = itemView.findViewById(R.id.idFrom);
            value = itemView.findViewById(R.id.value);
            date = itemView.findViewById(R.id.dateTransfer);
            view = itemView.findViewById(R.id.itemExtract);
        }
    }
}
