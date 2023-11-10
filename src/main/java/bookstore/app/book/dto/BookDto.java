package bookstore.app.book.dto;

import lombok.Data;


@Data
public class BookDto {
    private Long id;

    private String title;

    private String author;

    private String category;

    private String releaseDate;

    private Double price;

    private String imgCover;

}