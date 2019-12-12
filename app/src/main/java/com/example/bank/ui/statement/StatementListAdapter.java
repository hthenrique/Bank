package com.example.bank.ui.statement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.Model.StatementModel;
import com.example.bank.R;

import java.util.List;

public class StatementListAdapter extends RecyclerView.Adapter<StatementListAdapter.ViewHolder> {

    private List<StatementModel> mStatement;

    StatementListAdapter(List<StatementModel> statement){
        setList(statement);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.item_statement, parent, false);
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementListAdapter.ViewHolder holder, int position) {
        StatementModel statement = mStatement.get(position);
        holder.id_user_to.setText(statement.id_to);
        holder.id_user_from.setText(statement.id_from);
        holder.value.setText(statement.value);
        holder.date.setText(statement.data);
    }

    void replaceData(List<StatementModel> statement){
        setList(statement);
        notifyDataSetChanged();
    }

    private void setList(List<StatementModel> statement) {
        mStatement = statement;
    }

    @Override
    public int getItemCount() {
        return mStatement.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView id_user_to;
        TextView id_user_from;
        TextView value;
        TextView date;
        View view;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_user_to = itemView.findViewById(R.id.idTo);
            id_user_from = itemView.findViewById(R.id.idFrom);
            value = itemView.findViewById(R.id.value);
            date = itemView.findViewById(R.id.dateTransfer);
            view = itemView.findViewById(R.id.itemStatement);
        }
    }
}
