package com.example.hrminiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hrminiapp.databinding.RecordLayoutBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  List<Employee> employeeList;
    RecordLayoutBinding recordBinding;

    public ListAdapter(List<Employee> emplist, Context context){
        super();
        this.employeeList = emplist;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        recordBinding = RecordLayoutBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(recordBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(employeeList.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        RecordLayoutBinding recyclerRowBinding;
        public ViewHolder(@NonNull RecordLayoutBinding recyclerRowBinding)
        {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
        public void bindView(Employee employee)
        {
            recyclerRowBinding.txtId.setText( String.valueOf(employee.getId()));
            recyclerRowBinding.txtName.setText(employee.getName());
            recyclerRowBinding.txtDesig.setText(employee.getDesig());
            recyclerRowBinding.txtDept.setText(employee.getDept());
            recyclerRowBinding.txtEmail.setText(employee.getEmailId());
            recyclerRowBinding.txtSalary.setText(String.valueOf(employee.getSalary()));
        }
    }
}