package de.jhulsch.library.common.mappers;

public abstract class BaseMapper<T,R> {

    public abstract T toPdo(R domainObject);

    public abstract R toDomainObject(T pdo);
}
