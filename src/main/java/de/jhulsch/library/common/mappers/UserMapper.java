package de.jhulsch.library.common.mappers;

import de.jhulsch.library.persistence.entity.UserPdo;
import de.jhulsch.library.service.domain.ImmutableUser;
import de.jhulsch.library.service.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper extends BaseMapper<UserPdo, User> {
    @Override
    public UserPdo toPdo(User domainObject) {
        UserPdo userPdo = new UserPdo();
        userPdo.setUserId(domainObject.getUserId());
        userPdo.setCreatedOn(domainObject.getCreatedOn());
        userPdo.setDeletedOn(domainObject.getDeletedOn().orElse(null));
        userPdo.setFirstname(domainObject.getFirstname());
        userPdo.setName(domainObject.getName());
        userPdo.setGender(domainObject.getGender());
        return userPdo;
    }

    @Override
    public User toDomainObject(UserPdo pdo) {
        return ImmutableUser.builder()
                .userId(pdo.getUserId())
                .name(pdo.getName())
                .firstname(pdo.getFirstname())
                .gender(pdo.getGender())
                .createdOn(pdo.getCreatedOn())
                .deletedOn(Optional.ofNullable(pdo.getDeletedOn()))
                .build();
    }
}
