package cheapFlights.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PostDownloader {
    private static final Logger log = LoggerFactory.getLogger(PostDownloader.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void downloadNewPosts() {
        log.info("New posts was downloaded at " + dateFormat.format(new Date()));
    }
}
