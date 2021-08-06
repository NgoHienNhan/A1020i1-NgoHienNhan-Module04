package vn.codegym.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Không được để trống")
    @Size(max = 50, message = "Tối đa 50 kí tự")
    private String name;
    @NotBlank(message = "Không được để trống")
    @Size(max = 50, message = "Tối đa 50 kí tự")
    @Column(unique = true)
    private String URLName;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Blog> blogs;
    @NotBlank
    private String img;

    public Category() {
    }

    public Category(String name, String URLName, List<Blog> blogs) {
        this.name = name;
        this.URLName = URLName;
        this.blogs = blogs;
    }

    public Category(Integer id, String name, String URLName, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.URLName = URLName;
        this.blogs = blogs;
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getURLName() {
        return URLName;
    }

    public void setURLName(String URLName) {
        this.URLName = URLName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
