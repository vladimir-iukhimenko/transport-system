package com.transportsystem.backend.embeddable;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
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
            parameters = { @Parameter( name = "transportsystem.model.Nomenclature", value = "java.util.HashMap" ) }
    )
    private Map<Integer, String> comments;

    @Column
    @Getter
    @Setter
    private Integer commentid;

    public Comment() {
        this.comments = new HashMap<>();
        this.commentid = 0;
    }

    public void addComment(String comment) {
        String text = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)).toString();
        comment = text + " " + comment;
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
