package api.restapi.domain.entity;

import api.restapi.enums.Position;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column
    private Date birthDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column
    @CreationTimestamp
    private Date createdAt;

    public Player() {
    }

    public Player(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Player(String name, String country, Date birthDate) {
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
    }

    public Player(String name, String country, Position position) {
        this.name = name;
        this.country = country;
        this.position = position;
    }

    public Player(String name, String country, Date birthDate, Position position) {
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
        this.position = position;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
