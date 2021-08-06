package vn.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlogShow {
    private Integer id;

    private String title;

    private String URLTitle;

    private String content;

    private String headContent;

    private Long views= 0L;
    private String date;

    private Category category;

    public BlogShow() {
    }

    public BlogShow(Blog blog){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.URLTitle = blog.getURLTitle();
        this.content = blog.getContent();
        this.headContent = blog.getHeadContent();
        this.views = blog.getViews();
        this.category = blog.getCategory();
        category.setBlogs(null);
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        this.date = formatter.format(blog.getDate());
    }

    public BlogShow(Integer id, String title, String URLTitle, String content, String headContent, Long views, String date, Category category) {
        this.id = id;
        this.title = title;
        this.URLTitle = URLTitle;
        this.content = content;
        this.headContent = headContent;
        this.views = views;
        this.date = date;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURLTitle() {
        return URLTitle;
    }

    public void setURLTitle(String URLTitle) {
        this.URLTitle = URLTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadContent() {
        return headContent;
    }

    public void setHeadContent(String headContent) {
        this.headContent = headContent;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
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
