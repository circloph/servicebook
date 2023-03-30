package net.kruglov.servicebook.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "clients")
public class Client extends User {

    private String numberPhone;

    public Client(String firstName, String middleName, String lastName, String login, String password, String numberPhone) {
        super(firstName, middleName, lastName, login, password);
        this.numberPhone = numberPhone;
    }

}
