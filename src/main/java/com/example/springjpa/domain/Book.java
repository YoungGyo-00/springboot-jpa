package com.example.springjpa.domain;

import com.example.springjpa.domain.converter.BookStatusConverter;
import com.example.springjpa.repository.dto.BookStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "deleted = false")
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @ToString.Exclude
    private Publisher publisher;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    private boolean deleted;

    private BookStatus status;

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors){
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }
}
