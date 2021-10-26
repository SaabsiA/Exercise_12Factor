package fhtw.msc.swe.aigensberger.exercise_12factor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TBLBLOGENTRY")
public class BlogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    @Column(nullable = false, length = 64)
    private String title;

    @Column(nullable = false, length = 2048)
    private String description;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "author_id")
    private Author author;

    public BlogEntry(String title, String description, LocalDate publicationDate, Author author) {
        this(null, title, description, publicationDate, author);
    }
}
