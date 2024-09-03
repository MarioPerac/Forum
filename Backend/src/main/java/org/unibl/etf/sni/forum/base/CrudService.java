package org.unibl.etf.sni.forum.base;


import org.unibl.etf.sni.forum.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.List;

public interface CrudService <ID extends Serializable> {

    <T> List<T> findAll(Class<T> resultDtoClass);
    <T> T findById(ID id, Class<T> resulDtoClass);
    <T,U> T insert(U object, Class<T> resultDtoClass);

    <T,U> List<T> insertAll(List<U> objects, Class<T> resultDtoClass);
    <T,U> T update(ID id, U object, Class<T> resultDtoClass) throws NotFoundException;
    void delete(ID id) throws NotFoundException;
}
