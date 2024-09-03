package org.unibl.etf.sni.forum.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.sni.forum.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Transactional
public class CrudJpaService<E extends BaseEntity<ID>, ID extends Serializable>  implements CrudService<ID>{


    protected JpaRepository<E, ID> jpaRepository;
    Class<E> entityClass;
    protected ModelMapper modelMapper;
    @PersistenceContext
    protected EntityManager entityManager;

    public CrudJpaService( JpaRepository<E, ID> jpaRepository,Class<E> entityClass, ModelMapper modelMapper){
        this.jpaRepository = jpaRepository;
        this.entityClass = entityClass;
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    public <T>  List<T> findAll(Class<T> resultDtoClass){
        return jpaRepository.findAll().stream().map(o -> modelMapper.map(o, resultDtoClass)).collect(Collectors.toList());
    }

    @Override
    public  <T> T findById(ID id, Class<T> resultDtoClass){
        return modelMapper.map(jpaRepository.findById(id), resultDtoClass);
    }

    @Override
    public <T,U> T insert(U object, Class<T> resultDtoClass){
        E entity = modelMapper.map(object, entityClass);
        if(entity.getId() instanceof Integer)
            entity.setId(null);
        entity = jpaRepository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity, resultDtoClass);
    }

    @Override
    public <T,U> List<T> insertAll(List<U> objects, Class<T> resultDtoClass){
        List<E> entities = objects.stream().map(o -> modelMapper.map(o, entityClass)).toList();
        entities.forEach(o -> o.setId(null));

        entities = jpaRepository.saveAllAndFlush(entities);
        entities.forEach(e -> entityManager.refresh(e));
        return entities.stream().map(e -> modelMapper.map(e, resultDtoClass)).collect(Collectors.toList());
    }
    @Override
    public <T,U> T update(ID id, U object, Class<T> resultDtoClass) throws NotFoundException {
        if(!jpaRepository.existsById(id)){
            throw new NotFoundException();
        }
        E entity = modelMapper.map(object, entityClass);
        entity.setId(id);
        entity = jpaRepository.saveAndFlush(entity);
        entityManager.refresh(entity);

        return modelMapper.map(entity, resultDtoClass);
    }
    @Override
    public void delete(ID id) throws NotFoundException {
        jpaRepository.deleteById(id);
    }
}
