package cheapFlights.controllers;

import cheapFlights.models.FanPage;
import cheapFlights.repositories.FanPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/")
public class FanPagesController {

    @Autowired
    private FanPageRepository fanPageRepository;

    @RequestMapping(value = "fanPages", method = RequestMethod.GET)
    public Iterable<FanPage> fanPages() {
        return fanPageRepository.findAll();
    }

}
