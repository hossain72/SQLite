package com.demo.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(@NonNull Context context, List<Student> studentList) {
        super(context, R.layout.item_student, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);

        TextView nameTV, ageTV, addressTV;

        nameTV = convertView.findViewById(R.id.nameTV);
        ageTV = convertView.findViewById(R.id.ageTV);
        addressTV = convertView.findViewById(R.id.addressTV);

        nameTV.setText(studentList.get(position).getName());
        ageTV.setText(String.valueOf(studentList.get(position).getAge()));
        addressTV.setText(studentList.get(position).getAddress());

        return convertView;
    }
}
