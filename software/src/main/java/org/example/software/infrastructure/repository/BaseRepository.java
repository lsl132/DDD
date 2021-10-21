package org.example.software.infrastructure.repository;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


/**
 *
 * @author SHK
 */
@Repository
public class BaseRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 保存实体对象
     *
     * @param entity
     */
    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }

    /**
     * 根据ID查询实体对象
     *
     * @param clazz
     * @param id
     * @return
     */
    public T findById(Class<T> clazz, ID id) {
        T t = entityManager.find(clazz, id);
        return t;
    }

    /**
     * 删除实体对象
     *
     * @param entity
     */
    @Transactional
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    /**
     * 替换实体属性
     */
    public void replace(T entity) {
        entityManager.merge(entity);
    }

}
