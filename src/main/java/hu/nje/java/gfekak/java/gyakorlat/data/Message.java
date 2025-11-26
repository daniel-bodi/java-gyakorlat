package hu.nje.java.gfekak.java.gyakorlat.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * @author danielbodi
 */
@Entity
@Table(name = "uzenetek")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "az")
    private Integer id;

    @NotBlank(message = "Név megadása kötelező")
    @Column(name = "nev", nullable = false)
    private String name;

    @Email(message = "Érvényes email cím szükséges")
    @NotBlank(message = "Email megadása kötelező")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Üzenet megadása kötelező")
    @Size(max = 250, message = "Üzenet maximum 250 karakter lehet")
    @Column(name = "uzenet", length = 250, nullable = false)
    private String content;

    private LocalDateTime sentAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
