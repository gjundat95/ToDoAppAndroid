package com.jundat95.todoapp.view.main;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jundat95.todoapp.R;
import com.jundat95.todoapp.model.ToDo;

import java.util.List;

/**
 * Created by tinhngo on 19/10/2017.
 */

public class ToDoAdapter extends ArrayAdapter<ToDo> {

    private List<ToDo> toDoList;
    private Context context;

    public ToDoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ToDo> toDos) {
        super(context, resource, toDos);
        this.context = context;
        this.toDoList = toDos;

    }

    // Hàm lấy ra số phần tử của danh sách
    @Override
    public int getCount() {
        return toDoList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        // Ánh xạ đến layout item_to_do để xử dụng các component nằm trong đó
        View v =  LayoutInflater.from(context).inflate(R.layout.item_to_do_list_view,parent,false);

        // ánh xạ các component
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        TextView tvContent = v.findViewById(R.id.tvContent);
        TextView tvDate = v.findViewById(R.id.tvDate);

        // Set dữ liệu cho các compoent
        tvDate.setText(toDoList.get(position).getDate());
        tvContent.setText(toDoList.get(position).getContent());
        tvTitle.setText(toDoList.get(position).getTitle());

        return v;
    }
}
