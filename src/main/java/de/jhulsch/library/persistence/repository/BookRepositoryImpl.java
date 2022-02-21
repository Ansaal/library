package de.jhulsch.library.persistence.repository;

import de.jhulsch.library.common.mappers.BookMapper;
import de.jhulsch.library.persistence.entity.BookPdo;
import de.jhulsch.library.persistence.entity.BookPdo_;
import de.jhulsch.library.persistence.entity.UserBookMappingPdo;
import de.jhulsch.library.persistence.entity.UserBookMappingPdo_;
import de.jhulsch.library.service.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class BookRepositoryImpl  extends AbstractRepository implements BookRepository{

    private EntityManager entityManager;

    private final BookMapper bookMapper;

    @Autowired
    public BookRepositoryImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public Stream<Book> getBooksFromUserBetweenDates(UUID userId, LocalDate from, LocalDate book) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Stream<Book> getAvailableBooks() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<BookPdo> query = cb.createQuery(BookPdo.class);
        Root<BookPdo> root = query.from(BookPdo.class);


        root.join(BookPdo_.borrowedBooks,JoinType.LEFT);

        Subquery<UserBookMappingPdo> subquery = query.subquery(UserBookMappingPdo.class);

        Root<UserBookMappingPdo> sqRoot = subquery.from(UserBookMappingPdo.class);
        subquery.select(sqRoot);

        Predicate sqPredicate = cb.equal(root.get(BookPdo_.BOOK_ID), sqRoot.get(UserBookMappingPdo_.BOOK));
        Predicate sqPredicate2 = cb.greaterThanOrEqualTo(sqRoot.get(UserBookMappingPdo_.BORROWED_TO), LocalDate.now());

        subquery.where(cb.and(sqPredicate,sqPredicate2));


        query.select(root).distinct(true).where(cb.not(
                    cb.exists(subquery)
            ));

        TypedQuery<BookPdo> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultStream().map(this.bookMapper::toDomainObject);


    }


    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
