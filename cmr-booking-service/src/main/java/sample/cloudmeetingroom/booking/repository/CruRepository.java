package sample.cloudmeetingroom.booking.repository;

import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * @author Lionel Ngounou
 */
@NoRepositoryBean
public interface CruRepository<T extends Object, ID extends Serializable> extends Repository<T, ID> {

    public <S extends T> S save(S s);

    //public <S extends T> Iterable<S> save(Iterable<S> itrbl);

    public Optional<T> findById(ID id);

    public boolean exists(ID id);

    public long count();
}
