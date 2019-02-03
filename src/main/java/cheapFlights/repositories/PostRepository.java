package cheapFlights.repositories;

import cheapFlights.models.FanPage;
import cheapFlights.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    @Query("select p from cheapFlights.models.Post p where p.fbId = ?1")
    Post findByFbId(String fbId);

    @Query("select p from cheapFlights.models.Post p where p.fanPage = ?1")
    Page<Post> findAllBy(Pageable pageable, FanPage fanPage);
}
