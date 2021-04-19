package com.qdqtrj.lianlian.accp.service.impl;

import com.qdqtrj.lianlian.accp.repo.IbaseDao;
import com.qdqtrj.lianlian.accp.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @param <E>
 * @param <ObjectId>
 * @author yinbin
 */
public class BaseServiceImpl<E, ObjectId> implements IBaseService<E, ObjectId> {

    @Autowired
    private IbaseDao<E, ObjectId> repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<E> clz;

    public Class<E> getClz() {
        if (clz == null) {
            clz = (Class<E>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        }
        return clz;
    }

    @Override
    public E save(E entity) {

        return this.repo.save(entity);
    }

    @Override
    public void saveBatch(List<E> list) {
        this.repo.saveAll(list);

    }

    @Override
    public void deleteById(ObjectId pk) {
        this.repo.deleteById(pk);

    }

    @Override
    public List<E> findAll() {

        return this.repo.findAll();
    }

    @Override
    public List<E> findByIds(List<ObjectId> pk) {

        return (List<E>) this.repo.findAllById(pk);

    }

    @Override
    public E findById(ObjectId pk) {

        return (E) this.repo.findById(pk);
    }

    @Override
    public void update(ObjectId id, E t) {
        this.repo.update(id, t);

    }

    @Override
    public void update(ObjectId id, Map<String, Object> updateFieldMap) {
        this.repo.update(id, updateFieldMap);
    }

    @Override
    public void update(Map<String, Object> queryFieldMap, Map<String, Object> updateFieldMap) {
        this.repo.update(queryFieldMap, updateFieldMap);

    }

}
