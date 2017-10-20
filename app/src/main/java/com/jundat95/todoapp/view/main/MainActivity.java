package com.jundat95.todoapp.view.main;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jundat95.todoapp.R;
import com.jundat95.todoapp.model.ToDo;
import com.jundat95.todoapp.sqlite.SQLiteHelper;
import com.jundat95.todoapp.view.add.AddActivity;
import com.jundat95.todoapp.view.detail.DetailToDoActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this, 2);
    private ToDoAdapter toDoAdapter;

    private ListView listView;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        //addToDo();
        final List<ToDo> toDos = getToDo();

        toDoAdapter = new ToDoAdapter(this, R.layout.item_to_do_list_view, toDos);
        listView.setAdapter(toDoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailToDoActivity.class);
                intent.putExtra("title", toDos.get(i).getTitle());
                intent.putExtra("content", toDos.get(i).getContent());
                intent.putExtra("date", toDos.get(i).getDate());
                startActivity(intent);
            }
        });

    }

    private void init() {

        listView = (ListView) findViewById(R.id.listView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addToDo() {
        ContentValues contentValues = new ContentValues();

        String time = new Date().getTime() + "";
        String date = new Date() +  "";

        contentValues.put(SQLiteHelper.Id, time);
        contentValues.put(SQLiteHelper.Title, "Hello "+ time);
        contentValues.put(SQLiteHelper.Date, date);
        contentValues.put(SQLiteHelper.Content, "Xin chao ba con " + time);

        long n = sqLiteHelper.addToDo(contentValues);
        Toast.makeText(this, "Add complete: " + n, Toast.LENGTH_SHORT).show();
    }


    private List<ToDo> getToDo() {
        List<ToDo> toDoList = new ArrayList<>();
        Cursor cursor = sqLiteHelper.getTodos();
        String data = "";
        while (cursor.moveToNext()){
            ToDo toDo = new ToDo(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );

            toDoList.add(toDo);

        }
        return toDoList;
    }

}
