package cheapFlights.repositories;

import cheapFlights.models.FanPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FanPageRepository extends PagingAndSortingRepository<FanPage, Long> {
    @Query("select fp from cheapFlights.models.FanPage fp where fp.active = true")
    public Iterable<FanPage> findAllActive();
}
