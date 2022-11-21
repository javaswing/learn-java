package taco.api;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import taco.data.Taco;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
