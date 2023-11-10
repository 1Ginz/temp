package bookstore.app.book.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorSequence")
    @SequenceGenerator(name = "generatorSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
