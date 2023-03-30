package net.kruglov.servicebook.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "books")
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private String genre;
    @NonNull
    private String description;
    @NonNull
    private Integer yearOfProduction;
    @ManyToMany(targetEntity = Author.class, cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "books_authors", 
        joinColumns = { @JoinColumn(name = "book_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private List<Author> authors;
    @OneToMany(mappedBy = "book")
    private List<Reviews> reviews; 
}
