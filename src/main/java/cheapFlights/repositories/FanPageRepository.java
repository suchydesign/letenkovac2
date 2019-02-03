package cheapFlights.repositories;

import cheapFlights.models.FanPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FanPageRepository extends PagingAndSortingRepository<FanPage, Long> {
    @Query("select fp from cheapFlights.models.FanPage fp where fp.active = true")
    Iterable<FanPage> findAllActive();

    @Query("select fp from cheapFlights.models.FanPage fp where fp.fbId = ?1")
    FanPage findByFbId(String fbId);
}
