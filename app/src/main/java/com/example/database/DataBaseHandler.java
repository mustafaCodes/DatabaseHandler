package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1 ;
    public static final String DATABASE_NAME="StudentDatabase";
    public static final String TABLE_NAME="Student";
    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_MAJOR="major";

    public DataBaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_STUDENT_TABLE ="CREATE TABLE " +TABLE_NAME + "(" +KEY_ID +" INTEGER PRIMARY KEY,"+KEY_NAME +" TEXT, "+KEY_MAJOR +" TEXT" +")";
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student st){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();//وسيط
        contentValues.put(KEY_NAME, st.getName());//add ... put has two parameter
        contentValues.put(KEY_MAJOR, st.getMajor());//add
        db.insert(TABLE_NAME,null,contentValues);//take the content value content then add it to the table name.
        db.close();
    }
    public List<Student> getAllStudent(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<Student> studentList=new ArrayList<Student>();
        String selectQuery ="SELECT *FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null) ;
        if(cursor.moveToFirst()){
            do{
                Student student =new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setMajor(cursor.getString(2));
                studentList.add(student);
            }while (cursor.moveToNext());
        }
        return studentList;
    }
    public int UpdateStudent(Student student){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(KEY_MAJOR, student.getMajor());
        String selection =KEY_NAME +"=?";
        String [] selectionArgs= {student.getName()};
        return db.update(TABLE_NAME,contentValues,selection,selectionArgs);
    }
    public Student getStudent(String name){
        SQLiteDatabase db=this.getReadableDatabase();
        String [] projection ={KEY_ID,KEY_NAME,KEY_MAJOR};
        String selection= KEY_NAME + "=?";
        String [] selectionArgs = {name};//القيمة اللي بدي ابحث عنها
        Student student=null;
        Cursor cursor=db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,null,null);
        if(cursor != null && cursor.moveToFirst()){
            student=new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2) );
        }
        return student;
    }

    public int getStudentCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String countQuery="SELECT *FROM "+TABLE_NAME;
        Cursor cursor =db.rawQuery(countQuery ,null);
        int n=cursor.getCount();//get count built in method
        cursor.close();
        return n;
    }

    public void deleteStudent(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String selection=KEY_NAME+"=?"; //اسم الحقل الذي سابحث بداخله
        String [] selectionArgs={name};
        db.delete(TABLE_NAME,selection,selectionArgs);
        db.close();
    }



}
