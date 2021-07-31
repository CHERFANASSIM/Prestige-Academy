package com.isika.prestigeacademy.repositories.dao;

import com.isika.prestigeacademy.repositories.idao.GenericIDAO;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Remote(GenericIDAO.class)
@Stateless
public abstract class GenericDao<T> implements GenericIDAO<T> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public T add(T t) {
        em.persist(t);
        em.flush();
        return t;
    }

    @Override
    public boolean delete(T t) {
        boolean removed;
        try {
            t = em.merge(t);
            em.remove(t);
            removed = true;
        }catch (Exception e) {
            e.printStackTrace();
            removed = false;
        }
        return removed;
    }

    @Override
    public T update(T t) {
        em.merge(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Long i) {
        T t = null;
        try {
            String className= ((ParameterizedType) getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            Class<?> clazz;
            clazz = Class.forName(className);
            t= (T) em.find(clazz, i);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    public List<T> findeAll() {
        List<T> objects = null;
        try {
            String className= ((ParameterizedType) getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            Class<?> clazz;
            clazz = Class.forName(className);
            Query query = em.createQuery("FROM " + clazz.getName());
            objects = query.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }
}
