package bookstore.app.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.lang.String;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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


    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "img_cover", nullable = true)
    private String imgCover;

    @ManyToOne
    @JsonIgnoreProperties(value = {"books"},allowSetters = true)
    private Category category;

    @Transient
    public String getImgCover(){
        if(this.imgCover == null) return null;
        return "/data/" + this.id + "/" + this.imgCover;
    }

}
