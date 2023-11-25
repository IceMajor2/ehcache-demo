package ehcache.java.demo.db.dao;

import java.util.List;

public interface Dao<T> {

    T findById(Long id);
    List<T> findAll();
    void save(T object);
}
