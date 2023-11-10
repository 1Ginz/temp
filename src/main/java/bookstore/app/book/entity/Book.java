package bookstore.app.book.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.lang.String;

@Entity
@Table(name = "Book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "not blank book title")
    private String title;

    @Column(name = "author")
    @NotBlank(message = "not blank book author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "img_cover", nullable = true)
    private String imgCover;

    @Transient
    public String getImgCover(){
        if(this.imgCover == null) return null;
        return "/data/coverImg" + this.imgCover;
    }

}
