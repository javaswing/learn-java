package taco.data;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
