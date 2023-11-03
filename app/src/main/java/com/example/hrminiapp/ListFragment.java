package com.example.hrminiapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hrminiapp.databinding.FragmentAddBinding;
import com.example.hrminiapp.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    FragmentListBinding listBinding;

    List<Employee> mList = new ArrayList<Employee>();
    DBHelper dbh;
    ListAdapter mAdapter;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listBinding = FragmentListBinding.inflate(inflater,container, false);
        View view = listBinding.getRoot();
        init();
        return view;
    }

    private void init(){
        dbh = new DBHelper(getActivity());
        Cursor cursor1 = dbh.readEmployees();
        if(cursor1 == null){
            Toast.makeText(getActivity(), "No employees record found", Toast.LENGTH_SHORT).show();
        }
        else{
            cursor1.moveToFirst();

            do{
                Employee empObj = new Employee();
                empObj.setId(cursor1.getInt(0));
                empObj.setName(cursor1.getString(1));
                empObj.setDesig(cursor1.getString(2));
                empObj.setDept(cursor1.getString(3));
                empObj.setEmailId(cursor1.getString(4));
                empObj.setSalary(cursor1.getInt(5));
                mList.add(empObj);
            }while (cursor1.moveToNext());
            cursor1.close();
            dbh.close();
            bindAdapter();
        }
    }

    private void bindAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listBinding.rcView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(mList, getContext());
        listBinding.rcView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}