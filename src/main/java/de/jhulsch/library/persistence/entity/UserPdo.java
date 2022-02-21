package de.jhulsch.library.persistence.entity;

import de.jhulsch.library.common.Gender;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class UserPdo {

    @Id
    @Type(type="uuid-char")
    @Column(name="ID",nullable = false)
    private UUID userId;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "FIRSTNAME",nullable = false)
    private String firstname;

    @Column(name = "GENDER",nullable = false)
    private Gender gender;

    @Column(name = "CREATED_ON",nullable = false)
    private LocalDate createdOn;

    @Column(name = "DELETED_ON",nullable = true)
    private LocalDate deletedOn;

    @OneToMany(mappedBy = "user" )
    private Set<UserBookMappingPdo> borrowedBooks;

    public UserPdo() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(LocalDate deletedOn) {
        this.deletedOn = deletedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPdo userPdo = (UserPdo) o;
        return userId.equals(userPdo.userId) && name.equals(userPdo.name) && firstname.equals(userPdo.firstname) && gender == userPdo.gender && createdOn.equals(userPdo.createdOn) && Objects.equals(deletedOn, userPdo.deletedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, firstname, gender, createdOn, deletedOn);
    }

    @Override
    public String toString() {
        return "UserPdo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", gender=" + gender +
                ", createdOn=" + createdOn +
                ", deletedOn=" + deletedOn +
                '}';
    }
}
