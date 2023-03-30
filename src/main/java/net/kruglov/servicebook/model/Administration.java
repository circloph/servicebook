package net.kruglov.servicebook.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@NoArgsConstructor
@Table(name = "administration")
public class Administration extends User {

    private String position;
    
    public Administration(String firstName, String middleName, String lastName, String login, String password, String position) {
        super(firstName, middleName, lastName, login, password);
        this.position = position;
    }
}
