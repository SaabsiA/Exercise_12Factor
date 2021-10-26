package fhtw.msc.swe.aigensberger.exercise_12factor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogEntryListItemDto {

    long oid;
    String title;

    @JsonProperty("author")
    String authorName;

    public static BlogEntryListItemDto of(BlogEntry blogEntry) {
        return new BlogEntryListItemDto(
                blogEntry.getOid(),
                blogEntry.getTitle(),
                getFullName(blogEntry.getAuthor())
        );
    }

    private static String getFullName(Author author) {
        return author.getFirstName() + " " + author.getLastName();
    }
}
