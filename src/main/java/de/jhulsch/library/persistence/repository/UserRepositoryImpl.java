package de.jhulsch.library.persistence.repository;

import de.jhulsch.library.common.mappers.UserMapper;
import de.jhulsch.library.persistence.entity.*;
import de.jhulsch.library.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.stream.Stream;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    private final EntityManager entityManager;

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager, UserMapper userMapper) {
        this.entityManager = entityManager;
        this.userMapper = userMapper;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public Stream<User> findAllActiveUsersWithoutBook() {

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserPdo> query = cb.createQuery(UserPdo.class);
        Root<UserPdo> root = query.from(UserPdo.class);


        root.join(UserPdo_.borrowedBooks, JoinType.LEFT);

        Subquery<UserBookMappingPdo> subquery = query.subquery(UserBookMappingPdo.class);

        Root<UserBookMappingPdo> sqRoot = subquery.from(UserBookMappingPdo.class);
        subquery.select(sqRoot);

        Predicate sqPredicate = cb.equal(root.get(UserPdo_.USER_ID), sqRoot.get(UserBookMappingPdo_.USER));
        Predicate sqPredicate2 = cb.greaterThanOrEqualTo(sqRoot.get(UserBookMappingPdo_.BORROWED_TO), LocalDate.now());

        subquery.where(cb.and(sqPredicate,sqPredicate2));


        query.select(root).distinct(true).where(cb.not(
                cb.exists(subquery)
        ));

        TypedQuery<UserPdo> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultStream().map(this.userMapper::toDomainObject);



    }

    @Override
    public Stream<User> findAllBorrowersAtDate(LocalDate date) {
        throw new RuntimeException("Not yet implemented");
    }
}
