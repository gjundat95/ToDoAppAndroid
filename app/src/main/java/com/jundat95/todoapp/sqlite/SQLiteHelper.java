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
        // ham khoi tao class, khi chay se tao ra mot cai database
        super(context, Database_Name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Lan dau tien chay se tao ra cac bang du lieu
        // Khi tang version > hon version cu cung se chay vao day

        // Lệnh tạo bảng trong SQL
        // Lưu ý các xâu đầu xâu cuối xâu đều có dấu cách
        String createTableTodo = " create table "+ Table_Todo + " ( "
                + Id + " text primary key , "
                + Title + " text , "
                + Date + " text, "
                + Content + " text ) "
                ;

        // Tạo bảng
        sqLiteDatabase.execSQL(createTableTodo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Ham chay khi version moi > version cu

        // Lenh Sql xoa bang
        String dropTableTodo = "drop table if exists " + Table_Todo;
        // Thu hien xoa bang
        sqLiteDatabase.execSQL(dropTableTodo);

        // Sau khi xoa bang xong, chung ta chay lai ham onCreate o tren
        onCreate(sqLiteDatabase);
        // Lúc này khi cập nhật các cột cho bảng thì nó chạy lại hàm tạo mới
        // Lúc này tạo ra cái bảng mới có các cột đã được sửa đổi và không có gì ở trong
    }

    /***
     * Cách 1
     * Lấy toàn bộ dữ liệu trong bảng Todo
     * Lưu vào con trỏ cursor
     * @return Cursor
     */
    public Cursor getTodos() {
        // getReadableDatabase là sử dụng quyền chỉ đọc dữ liệu chứ không nghi được
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Lệnh SQL lấy toàn bộ bảng Todo
        String query = " select * from " + Table_Todo;

        // Thực hiện lệnh SQL, kết quả lưu vào Cursor( Con trỏ )
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    /***
     * Cách 2
     * Lấy toàn bộ dữ liệu trong ToDo
     * Dữ liệu lấy về lưu vào danh sách
     * @return List<ToDo>
     */
    public List<ToDo> getToDos() {
        // Tạo một danh sách Todo từ mode
        List<ToDo> toDos = new ArrayList<>();

        // getReadableDatabase là sử dụng quyền chỉ đọc dữ liệu chứ không nghi được
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Lệnh SQL lấy toàn bộ bảng Todo
        String query = " select * from " + Table_Todo;

        // Thực hiện lệnh SQL, kết quả lưu vào Cursor( Con trỏ )
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        // Duyệt toàn bộ con trỏ khi còn dữ liệu
        while (cursor.moveToNext()){
            // Tạo ra một đối tượng Todo để lưu trữ dữ liệu
            // cursor.getString(int i) - i là thứ tự của các cột trong bảng
            ToDo toDo = new ToDo(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );

            // Thêm 1 đối tượng vào danh sách
            toDos.add(toDo);
        }

        return toDos;
    }

    /***
     * Thêm dữ liệu vào bảng
     * Trả lại số dòng thêm được vào bảng, ví dụ thêm hai dòng nó sẽ trả về là 2
     * @param contentValues
     * @return long
     */
    public long addToDo(ContentValues contentValues) {
        // getWritableDatabase là sử dụng quyền vừa đọc dữ liệu vừa nghi được
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // hàm insert có 3 tham số truyền vào
        // tên bảng cần thêm, danh sách các cột không thêm, dữ liệu truyền vào contentValue
        return sqLiteDatabase.insert(Table_Todo, null, contentValues);
    }

}
