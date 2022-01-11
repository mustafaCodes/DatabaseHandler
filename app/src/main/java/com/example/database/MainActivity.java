package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText t1,t2;
    DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText) findViewById(R.id.editText1);
        t2=(EditText) findViewById(R.id.editText2);
        db=new DataBaseHandler(this);
    }

    public void SaveStudent(View v){
        String name= t1.getText().toString();
        String major= t2.getText().toString();
        Student s=new Student(name,major);
        List<Student> students=db.getAllStudent();
        for(Student st: students){
            if(st.getName().equalsIgnoreCase(name)){
                Toast.makeText(this, "The Student is Already exists", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        db.addStudent(s);
        Toast.makeText(this, "Added Succesfully", Toast.LENGTH_LONG).show();
        t1.setText(" ");
        t2.setText(" ");
    }
    public void UpdateStudent (View v){
        Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
        startActivity(intent);
    }
    public void DeleteStudent (View v){
        Intent intent=new Intent(MainActivity.this,DeleteActivity.class);
        startActivity(intent);
    }
    public void Show(View v){
        Intent intent=new Intent(MainActivity.this,ActList.class);
        startActivity(intent);
    }
}