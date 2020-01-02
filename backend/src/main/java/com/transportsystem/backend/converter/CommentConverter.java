package com.transportsystem.backend.converter;

import com.transportsystem.backend.embeddable.Comment;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CommentConverter implements AttributeConverter<Comment, String> {

    @Override
    public String convertToDatabaseColumn(Comment comment) {
        return comment.toString();
    }

    @Override
    public Comment convertToEntityAttribute(String str) {
        return Comment.fromString(str);
    }
}
