package com.example.demo.bean;

import org.springframework.web.multipart.MultipartFile;

public class BlogForm {
    private String title;
    private String content;
    private MultipartFile linkImg;
    private String date;
    private Category category;

    public BlogForm() {
    }

    public BlogForm(String title, String content, MultipartFile linkImg, String date, Category category) {
        this.title = title;
        this.content = content;
        this.linkImg = linkImg;
        this.date = date;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(MultipartFile linkImg) {
        this.linkImg = linkImg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
