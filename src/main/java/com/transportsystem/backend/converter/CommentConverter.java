package com.transportsystem.backend.converter;

import com.transportsystem.backend.embeddable.Comment;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CommentConverter implements AttributeConverter<Comment, String> {

    @Override
    public String convertToDatabaseColumn(Comment comment) {

        if (comment == null) return new Comment().toString();
        return comment.toString();
    }

    @Override
    public Comment convertToEntityAttribute(String str) {
        return Comment.fromString(str);
    }
}
