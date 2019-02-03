package cheapFlights.models;

import cheapFlights.helpers.StringHelper;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fb_id")
    private String fbId;

    @Column
    private String message;

    @Column
    private String link;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String picture;

    @Column
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fan_page_id")
    private FanPage fanPage;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Post() {}

    public Post(org.springframework.social.facebook.api.Post post, FanPage fanPage) {
        setFbId(post.getId());
        setMessage(StringHelper.removeMb4Chars(post.getMessage()));
        setLink(post.getLink());
        setName(StringHelper.removeMb4Chars(post.getName()));
        setDescription(StringHelper.removeMb4Chars(post.getDescription()));
        setPicture(post.getPicture());
        if(post.getType() != null && post.getType().name() != null) {
            setStatus(PostTypes.get(post.getType().name()));
        }
        setFanPage(fanPage);
        setCreatedTime(post.getCreatedTime());
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FanPage getFanPage() {
        return fanPage;
    }

    public void setFanPage(FanPage fanPage) {
        this.fanPage = fanPage;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
