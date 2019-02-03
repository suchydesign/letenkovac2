package cheapFlights.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;


}
