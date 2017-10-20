package com.jundat95.todoapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jundat95.todoapp.model.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinhngo on 12/10/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "ICTU";
    public static final String Table_Todo = "ToDo";
    public static final String Id = "Id";
    public static final String Title = "Title";
    public static final String Date = "Date";
    public static final String Content = "Content";

    public SQLiteHelper(Context context, int version) {
        super(context, Database_Name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableTodo = " create table "+ Table_Todo + " ( "
                + Id + " text primary key , "
                + Title + " text , "
                + Date + " text, "
                + Content + " text ) "
                ;
        sqLiteDatabase.execSQL(createTableTodo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableTodo = "drop table if exists " + Table_Todo;
        sqLiteDatabase.execSQL(dropTableTodo);
        onCreate(sqLiteDatabase);
    }


    public Cursor getTodos() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " select * from " + Table_Todo;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }


    public List<ToDo> getToDos() {
        List<ToDo> toDos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " select * from " + Table_Todo;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext()){
            ToDo toDo = new ToDo(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );

            toDos.add(toDo);
        }

        return toDos;
    }


    public long addToDo(ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.insert(Table_Todo, null, contentValues);
    }

}
