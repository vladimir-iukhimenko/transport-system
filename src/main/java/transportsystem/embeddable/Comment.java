package transportsystem.embeddable;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DZCKJS0 on 10.12.2019.
 */
@Embeddable
public class Comment {

    @Column
    @Getter
    @Setter
    @org.hibernate.annotations.Type(
            type = "org.hibernate.type.SerializableToBlobType",
            parameters = { @Parameter( name = "Nomenclature", value = "java.util.HashMap" ) }
    )
    private Map<Integer, String> comments;

    @Column
    @Getter
    @Setter
    Integer commentid = 0;

    public void addComment(String comment) {
        this.comments.put(commentid,comment);
        commentid++;
    }

    public String getComment(Integer id) {
        return this.comments.get(id);
    }

    public List<String> getAllComments() {
        List<String> arraycomments = new ArrayList<>();
        for(String i: this.comments.values()) {arraycomments.add(i);}
        return arraycomments;
    }

    public void clearComments() {
        this.comments.clear();
    }

}
