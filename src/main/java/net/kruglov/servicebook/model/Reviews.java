package net.kruglov.servicebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "reviews")
public class Reviews {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private Integer mark;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

}
