package cheapFlights.controllers;

import cheapFlights.models.Post;
import cheapFlights.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "posts", method = RequestMethod.GET)
    public Iterable<Post> posts() {
        return postRepository.findAll();
    }

}
