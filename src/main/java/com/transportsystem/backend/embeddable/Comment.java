package com.transportsystem.backend.embeddable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import java.io.Serializable;
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
@Data
public class Comment implements Serializable {

    private Map<Integer, String> comments;

    private Integer commentid;

    public Comment() {
        this.comments = new HashMap<>();
        this.commentid = 0;
    }

    public void addComment(String comment) {
        String text = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)).toString();
        comment = text + " " + comment;
        pushComment(comment);
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

    @Override
    public String toString() {
        StringBuilder comment = new StringBuilder();
        if (!comments.isEmpty()) comments.values().forEach((value) -> comment.append(value).append(";"));
        return comment.toString();
    }

    public static Comment fromString(String str) {
        if (str.equals("")) return new Comment();
        String[] splitted = str.split(";");
        Comment comments = new Comment();
        for(String comment: splitted) comments.pushComment(comment);
        return comments;
    }

    private void pushComment(String comment) {
        this.comments.put(commentid,comment);
        commentid++;
    }

}
