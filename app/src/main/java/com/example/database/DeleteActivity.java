package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    TextView t5;
    DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        t5=(TextView) findViewById(R.id.editText5);
        db = new DataBaseHandler(this);
    }
    public void deleteStudentData(View v){
        String name=t5.getText().toString();
        Student student=db.getStudent(name);
        if(student != null){
            db.deleteStudent(name);
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            t5.setText("");
            return;
        }
        Toast.makeText(this, "name"+" not exist!!", Toast.LENGTH_SHORT).show();
    }

}