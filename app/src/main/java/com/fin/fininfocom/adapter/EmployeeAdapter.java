package com.fin.fininfocom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fin.fininfocom.R;
import com.fin.fininfocom.model.EmployeeModel;

import java.util.ArrayList;

/**
 * Created by Regeti Jhansi Rani  on 09-03-2023.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>{
    ArrayList<EmployeeModel> list;
    Context context;

    public EmployeeAdapter(ArrayList<EmployeeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.employee_name.setText(list.get(position).getEmployee_name());
        holder.employee_age.setText(""+list.get(position).getEmployee_age());
        holder.employee_city.setText(list.get(position).getEmployee_city());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView employee_name, employee_age, employee_city;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            employee_name = itemView.findViewById(R.id.employee_name);
            employee_age = itemView.findViewById(R.id.employee_age);
            employee_city = itemView.findViewById(R.id.employee_city);
        }
    }
}
