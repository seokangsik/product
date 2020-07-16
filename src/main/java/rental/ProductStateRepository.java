package rental;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductStateRepository extends CrudRepository<ProductState, Long> {

    List<ProductState> findByItemId(Long itemId);
    

}
