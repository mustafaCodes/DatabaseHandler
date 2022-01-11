package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActList extends AppCompatActivity {
    ListView listView;
    DataBaseHandler db;
    ArrayAdapter<String> adapter;
    ArrayList<String> studentsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_list);

        listView =(ListView) findViewById(R.id.listview);

        db= new DataBaseHandler(this);

        studentsItems = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice,studentsItems);

        listView.setAdapter(adapter);

        List<Student> students = db.getAllStudent();

        for(Student st:students) {
            String studentData ="id:" +st.getId()+ ",name" + st.getName() + ",major:" + st.getMajor();
            studentsItems.add (0,studentData);
        }

        adapter.notifyDataSetChanged();


    }
}