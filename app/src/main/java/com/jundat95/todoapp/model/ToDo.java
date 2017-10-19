package com.jundat95.todoapp.model;

/**
 * Created by tinhngo on 12/10/2017.
 */

public class ToDo {

    private String Id;
    private String Title;
    private String Date;
    private String Content;

    public ToDo(String id, String title, String date, String content) {
        Id = id;
        Title = title;
        Date = date;
        Content = content;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
