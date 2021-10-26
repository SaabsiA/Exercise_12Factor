package fhtw.msc.swe.aigensberger.exercise_12factor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogEntryDto {

    long oid;
    String title;
    String description;
    LocalDate publicationDate;

    @JsonProperty("author")
    String authorName;

    public static BlogEntryDto of(BlogEntry blogEntry) {
        return new BlogEntryDto(
                blogEntry.getOid(),
                blogEntry.getTitle(),
                blogEntry.getDescription(),
                blogEntry.getPublicationDate(),
                getFullName(blogEntry.getAuthor())
        );
    }

    private static String getFullName(Author author) {
        return author.getFirstName() + " " + author.getLastName();
    }
}
