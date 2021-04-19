package com.qdqtrj.lianlian.accp.service;

import java.util.List;
import java.util.Map;

/**
 * @param <E>
 * @param <ObjectId>
 * @author yinbin
 */
public interface IBaseService<E, ObjectId> {

    /**
     * 添加一条数据
     *
     * @param entity 要添加的数据
     * @return 添加后生成的主键
     */
    E save(E entity);

    void saveBatch(List<E> list);

    /**
     * 根据传入的对象 修改
     *
     * @param id
     * @param t
     */
    void update(ObjectId id, E t);

    /**
     * 根据id修改
     *
     * @param id             更新主键
     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
     */
    void update(ObjectId id, Map<String, Object> updateFieldMap);

    /**
     * 根据传入值修改
     *
     * @param queryFieldMap  key:查询条件的属性  value:对应的属性值
     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
     */
    void update(Map<String, Object> queryFieldMap, Map<String, Object> updateFieldMap);

    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    void deleteById(ObjectId pk);

    List<E> findAll();

    List<E> findByIds(List<ObjectId> pk);

    E findById(ObjectId pk);
}
