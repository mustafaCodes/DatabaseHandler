package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText t1,t2;
    TextView v1,v2,v3;
    DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        t1=(EditText) findViewById(R.id.EditText3);
        t2=(EditText) findViewById(R.id.EditText4);
        v1=(TextView) findViewById(R.id.textView);
        v2=(TextView) findViewById(R.id.textView2);
        v3=(TextView) findViewById(R.id.textView3);
        db = new DataBaseHandler(this);
    }

    public void searchStudent(View v){
        String name=t1.getText().toString();
        Student student =db.getStudent(name);
        if(student != null){
            Toast.makeText(this, "Student exist", Toast.LENGTH_LONG).show();
            v1.setText("id: "+student.getId());
            v2.setText("name: "+student.getName());
            v3.setText("major: "+student.getMajor());
        }
        else{
            Toast.makeText(this, "No Student exist", Toast.LENGTH_LONG).show();
        }
    }
    public void updateStudentData(View v){
        String major =t2.getText().toString();
        Student student = new Student(t1.getText().toString(),major);
        int row=db.UpdateStudent(student);
        if(row>0){
            Toast.makeText(this, "Update Sucessfully", Toast.LENGTH_SHORT).show();
        }
    }

}