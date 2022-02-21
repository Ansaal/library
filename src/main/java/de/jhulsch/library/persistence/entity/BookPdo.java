package de.jhulsch.library.persistence.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "BOOKS")
public class BookPdo {

    @Column(name = "ID",nullable = false)
    @Type(type="uuid-char")
    @Id
    private UUID bookId;

    @Column(name = "TITLE",nullable = false)
    private String title;

    @Column(name = "AUTHOR",nullable = false)
    private String author;

    @Column(name = "GENRE",nullable = false)
    private String genre;

    @Column(name = "PUBLISHER",nullable = false)
    private String publisher;

    @OneToMany(mappedBy = "book" )
    private Set<UserBookMappingPdo> borrowedBooks;


    public BookPdo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public Set<UserBookMappingPdo> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<UserBookMappingPdo> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPdo bookPdo = (BookPdo) o;
        return bookId.equals(bookPdo.bookId) && title.equals(bookPdo.title) && author.equals(bookPdo.author) && genre.equals(bookPdo.genre) && publisher.equals(bookPdo.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, genre, publisher);
    }

    @Override
    public String toString() {
        return "BookPdo{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
