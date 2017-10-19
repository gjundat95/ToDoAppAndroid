package com.jundat95.todoapp.view.add;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jundat95.todoapp.R;
import com.jundat95.todoapp.sqlite.SQLiteHelper;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText edtTitle, edtDate, edtContent;

    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();
    }

    private void init() {
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtContent = (EditText) findViewById(R.id.edtContent);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodo();
            }
        });
    }

    private void addTodo() {
        // Tạo ra một Id random theo thoi gian
        long time = new Date().getTime();

        // Su dung contentValues để lưu dữ liệu, sau đó truyền vào SQLiteHelper
        ContentValues contentValues = new ContentValues();
        // Truyền vào contentValues dữ liệu có dạng [key, value]
        // Key là tên cột ở trong bảng, Value là giá trị muốn thêm vào cột đó

        contentValues.put("Id", time);
        contentValues.put("Title", edtTitle.getText().toString());
        contentValues.put("Date", edtDate.getText().toString());
        contentValues.put("Content", edtContent.getText().toString());

        // sử dụng phương thức addToDo với tham số là contentValues
        long n = sqLiteHelper.addToDo(contentValues);
        // n > 0 là thêm được dữ liệu vào trong bảng
        if(n > 0) {
            Toast.makeText(this, "Add complete", Toast.LENGTH_SHORT).show();
            // Destroy activity add
            finish();
        }
    }


}
