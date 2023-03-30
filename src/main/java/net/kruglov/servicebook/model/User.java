package net.kruglov.servicebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NonNull
    private String firstName;
    @NonNull
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
    private Role role;
    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude 
    private Reviews reviews;
}
