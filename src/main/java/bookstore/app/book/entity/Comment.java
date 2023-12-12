package bookstore.app.book.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "content")
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"comments", "category","orderDetails"}, allowSetters = true)
    private Book book;

//    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    @JsonIgnoreProperties(value = {"user", "comments"}, allowSetters = true)
//    private Account account;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"comments","account","orderTotal"})
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public Book getBook() {
        return this.book;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnoreProperties(value = {"comments", "category"}, allowSetters = true)
    public void setBook(Book book) {
        this.book = book;
    }



}
