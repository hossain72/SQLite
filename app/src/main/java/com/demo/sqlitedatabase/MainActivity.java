package com.demo.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET, ageET, addressET;
    private Button addStudentBtn, showStudent;
    private Student student;
    private StudentDatabaseSource studentDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        addressET = findViewById(R.id.addressET);
        addStudentBtn = findViewById(R.id.addStudentBtn);
        showStudent = findViewById(R.id.showStudentBtn);

        studentDatabaseSource = new StudentDatabaseSource(this);
        student = (Student) getIntent().getSerializableExtra("Student");

        if (student!=null){
            addStudentBtn.setText("Update Student");
            nameET.setText(student.getName());
            ageET.setText(String.valueOf(student.getAge()));
            addressET.setText(student.getAddress());
        }

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (student!=null){

                    String updateName = nameET.getText().toString();
                    int updateAge = Integer.valueOf(ageET.getText().toString());
                    String updateAddress = addressET.getText().toString();
                    int id = student.getId();

                    Student student = new Student(id, updateName, updateAge, updateAddress);
                    boolean updateStatus = studentDatabaseSource.updateStudent(student);
                    if (updateStatus) {
                        Toast.makeText(MainActivity.this, "Update Student", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Not update Student", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    String name = nameET.getText().toString();
                    int age = Integer.valueOf(ageET.getText().toString());
                    String address = addressET.getText().toString();

                    student = new Student(name, age, address);
                    boolean status = studentDatabaseSource.addStudent(student);

                    if (status) {
                        Toast.makeText(MainActivity.this, "Saved Student", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Not Saved Student", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        showStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudentActivity.class));
            }
        });




    }
}
