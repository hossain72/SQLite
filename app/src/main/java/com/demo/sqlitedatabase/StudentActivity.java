package com.demo.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity implements ItemListener {

    private ListView listView;
    private RecyclerView recyclerView;
    private StudentDatabaseSource studentDatabaseSource;
    private StudentAdapter studentAdapter;
    private StudentRecyclerViewAdapter adapter;
    private List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

  /*      listView = findViewById(R.id.listView);

        studentDatabaseSource = new StudentDatabaseSource(this);

        studentAdapter = new StudentAdapter(this, studentDatabaseSource.getAllStudent());
        listView.setAdapter(studentAdapter);*/

        recyclerView = findViewById(R.id.recyclerView);
        studentDatabaseSource = new StudentDatabaseSource(this);

        showStudent();

    }

    @Override
    public void update(Student student) {
        Intent intent = new Intent(StudentActivity.this, MainActivity.class);
        intent.putExtra("Student", student);
        startActivity(intent);
    }

    @Override
    public void delete(Student student) {

        boolean deleteStatus = studentDatabaseSource.deleteStudent(student);
        showStudent();
        if (deleteStatus) {
            Toast.makeText(this, "Delete Student", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete not Student", Toast.LENGTH_SHORT).show();
        }

    }


    public void showStudent() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentList = studentDatabaseSource.getAllStudent();
        adapter = new StudentRecyclerViewAdapter(this, studentList, this);
        recyclerView.setAdapter(adapter);

    }

}
