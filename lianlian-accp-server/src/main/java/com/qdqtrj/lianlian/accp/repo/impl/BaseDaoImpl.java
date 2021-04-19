package com.qdqtrj.lianlian.accp.repo.impl;

import com.qdqtrj.lianlian.accp.repo.IbaseDao;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.lang.reflect.Field;
import java.util.Map;


/**
 * @param <T>
 * @param <ObjectId>
 * @author yinbin
 */
public class BaseDaoImpl<T, ObjectId> extends SimpleMongoRepository<T, ObjectId> implements IbaseDao<T, ObjectId> {

    protected final MongoOperations mongoTemplate;
    protected final MongoEntityInformation<T, ObjectId> entityInformation;
    private final Class<T> clazz;

    public BaseDaoImpl(MongoEntityInformation<T, ObjectId> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoTemplate = mongoOperations;
        this.entityInformation = metadata;
        clazz = entityInformation.getJavaType();
    }

    @Override
    public void update(ObjectId id, T t) {
        Update update = new Update();
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        Field[] field = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            f.setAccessible(true);
            try {
                Object object = f.get(t);
                if (object != null) {
                    update.set(f.getName(), object);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.mongoTemplate.updateFirst(query, update, clazz);
    }

    @Override
    public void update(ObjectId id, Map<String, Object> updateFieldMap) {
        if (updateFieldMap != null && !updateFieldMap.isEmpty()) {
            Criteria criteria = new Criteria("_id").is(id);
            Update update = new Update();
            updateFieldMap.forEach(update::set);
            mongoTemplate.updateFirst(new Query(criteria), update, clazz);
        }
    }

    @Override
    public void update(Map<String, Object> queryFieldMap, Map<String, Object> updateFieldMap) {
        Criteria criteria = new Criteria();
        if (null != queryFieldMap && !queryFieldMap.isEmpty()) {
            queryFieldMap.forEach((key, value) -> criteria.and(key).is(value));
        }

        if (updateFieldMap != null && !updateFieldMap.isEmpty()) {
            Update update = new Update();
            updateFieldMap.forEach(update::set);
            mongoTemplate.updateFirst(new Query(criteria), update, clazz);
        }
    }
}
