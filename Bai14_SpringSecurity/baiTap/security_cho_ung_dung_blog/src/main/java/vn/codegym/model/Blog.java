package vn.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Không được để trống")
    @Size(max = 255, message = "Tối đa 255 kí tự")
    private String title;

    @NotBlank(message = "Không được để trống")
    @Size(max = 255, message = "Tối đa 255 kí tự")
    @Column(unique = true)
    private String URLTitle;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Không được để trống")
    private String content;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Không được để trống")
    private String headContent;

    private Long views= 0L;
    private Date date;

    private String dateDisplay;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Blog() {
    }

    public Blog(String title, String URLTitle, String content, Date date, Category category) {
        this.title = title;
        this.URLTitle = URLTitle;
        this.content = content;
        this.date = date;
        this.category = category;
    }

    public Blog(Integer id, String title, String URLTitle, String content, Date date, Category category) {
        this.id = id;
        this.title = title;
        this.URLTitle = URLTitle;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getURLTitle() {
        return URLTitle;
    }

    public void setURLTitle(String URLTitle) {
        this.URLTitle = URLTitle;
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

    public String getDateDisplay() {
        return dateDisplay;
    }

    public void setDateDisplay(String dateDisplay) {
        this.dateDisplay = dateDisplay;
    }
}
