package cheapFlights.controllers;

import cheapFlights.models.Post;
import cheapFlights.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/")
public class PostsController {
    private final int limit = 10;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "posts/{page}", method = RequestMethod.GET)
    public Iterable<Post> posts(@PathVariable(name = "page") int page) {
        Sort sort = new Sort(Sort.Direction.fromString("DESC"), Arrays.asList("id"));
        return postRepository.findAll(new PageRequest(page, limit, sort));
    }

}
