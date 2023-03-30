package net.kruglov.servicebook.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String shortBiography;
    @ManyToMany(targetEntity = Book.class, mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books;

}
