package cheapFlights.jobs;

import cheapFlights.helpers.StringHelper;
import cheapFlights.models.FanPage;
import cheapFlights.repositories.FanPageRepository;
import cheapFlights.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

@Component
@PropertySource("classpath:facebook.properties")
public class PostDownloader {
    @Autowired
    Environment env;
    @Autowired
    private FanPageRepository fanPageRepository;
    @Autowired
    private PostRepository postRepository;
    private static final Logger log = LoggerFactory.getLogger(PostDownloader.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private String appId;
    private String appSecret;
    private int newPostsCount;

    // every 30 minutes
//    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void downloadNewPosts() {
        appId = env.getProperty("spring.social.facebook.appId");
        appSecret = env.getProperty("spring.social.facebook.appSecret");
        newPostsCount = 0;

        Facebook facebook = new FacebookTemplate(appId + "|" + appSecret);
        Iterable<FanPage> pages = fanPageRepository.findAllActive();

        for(FanPage page : pages) {
            try {
                PagedList<Post> posts = facebook.feedOperations().getPosts(page.getFbId());
                importPostsForPage(page, posts);
            } catch(Exception e) {
                log.error("Page fbId = " + page.getFbId() + " was not accessible. Error - " + e.getMessage());
            }
        }
        log.info(newPostsCount + " new posts were downloaded.");
    }

    private boolean canCreate(Post post, String fanPageId) {
        return post != null &&
                postRepository.findByFbId(post.getId()) == null &&
                (
                        (
                                StringHelper.notEmpty(post.getMessage()) ||
                                StringHelper.notEmpty(post.getLink())
                        ) &&
                        fanPageId.equals(post.getFrom().getId())
                );
    }

    private void importPostsForPage(FanPage page, PagedList<Post> posts) throws Exception {
        int newPostsForPageCount = 0;
        for(Post post : posts) {
            if(canCreate(post, page.getFbId())) {
                try {
                    cheapFlights.models.Post newPost = new cheapFlights.models.Post(post, page);
                    postRepository.save(newPost);
                    newPostsCount++;
                    newPostsForPageCount++;
                } catch(Exception e) {
                    log.error("Post fbId = " + post.getId() + " was not saved. Error - " + e.getMessage());
                }
            } else {
                break;
            }
        }
        log.info(page.getName() + ": " + newPostsForPageCount + " posts were added.");
    }
}
