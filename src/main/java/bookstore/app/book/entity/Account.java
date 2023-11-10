package bookstore.app.book.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Table(name = "account")
@Entity
public class Account {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGengerator")
    @SequenceGenerator(name = "sequenceGengerator")
    private Long id;

    @Column(name = "user_name")
    @Size(min = 8, max = 50)
    private String username;

    @Column(name = "password")
    @Size(min = 8, max = 50)
    private String password;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "account")
    private User user;
}