package de.jhulsch.library.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "BOOKS_USERS_BORROWED")
public class UserBookMappingPdo implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "BOOK_ID" ,nullable = false)
    private BookPdo book;

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID" ,nullable = false)
    private UserPdo user;

    @Id
    @Column(name = "BORROWED_AT",nullable = false)
    private LocalDate borrowedAt;

    @Column(name = "BORROWED_TO",nullable = false)
    private LocalDate borrowedTo;



    public UserBookMappingPdo() {
    }

    public BookPdo getBook() {
        return book;
    }

    public void setBook(BookPdo book) {
        this.book = book;
    }

    public UserPdo getUser() {
        return user;
    }

    public void setUser(UserPdo user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBookMappingPdo that = (UserBookMappingPdo) o;
        return book.equals(that.book) && user.equals(that.user) && borrowedAt.equals(that.borrowedAt) && borrowedTo.equals(that.borrowedTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, user, borrowedAt, borrowedTo);
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDate borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public LocalDate getBorrowedTo() {
        return borrowedTo;
    }

    public void setBorrowedTo(LocalDate borrowedTo) {
        this.borrowedTo = borrowedTo;
    }

}
