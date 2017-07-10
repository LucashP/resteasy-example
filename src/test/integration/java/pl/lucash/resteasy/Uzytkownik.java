package pl.lucash.resteasy;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Audited
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String nowyEmail;

    public Uzytkownik() {
    }

    public Uzytkownik(String email, String nowyEmail) {
        this.email = email;
        this.nowyEmail = nowyEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNowyEmail() {
        return nowyEmail;
    }

    public void setNowyEmail(String nowyEmail) {
        this.nowyEmail = nowyEmail;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nowyEmail='" + nowyEmail + '\'' +
                '}';
    }
}
