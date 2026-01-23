package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "editions")
@Data
@Builder
public class EditionDocument {

    @Id
    private String id; // Edition id

    @Field(type = FieldType.Text, analyzer = "english")
    private String filmId;

    @Field(type = FieldType.Keyword)
    private String slug;

    @Field(type = FieldType.Text, analyzer = "english")
    private String filmTitle;

    @Field(type = FieldType.Binary)
    private String coverPicture;

    @Field(type = FieldType.Keyword)
    private String barCode;

    @Field(type = FieldType.Keyword)
    private String country;

    @Field(type = FieldType.Keyword)
    private String format;

    @Field(type = FieldType.Keyword)
    private Integer releaseYear;

    @Field(type = FieldType.Keyword)
    private String packagingType;

    @Field(type = FieldType.Text, analyzer = "english")
    private String notes;

    @Field(type = FieldType.Text, analyzer = "english")
    private String searchableText; // title + notes

    @Field(type = FieldType.Date)
    private LocalDateTime indexedAt;

}
