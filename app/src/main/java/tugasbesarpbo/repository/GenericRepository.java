
package tugasbesarpbo.repository;

import java.util.List;

public interface GenericRepository<T> {

    void save(T data) throws Exception;

    T findById(long id) throws Exception;

    List<T> findAll() throws Exception;

    void update(T data) throws Exception;

    void delete(long id) throws Exception;
}
