package cheapFlights.repositories;

import cheapFlights.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    @Query("select p from cheapFlights.models.Post p where p.fbId = ?1")
    Post findByFbId(String fbId);
}
