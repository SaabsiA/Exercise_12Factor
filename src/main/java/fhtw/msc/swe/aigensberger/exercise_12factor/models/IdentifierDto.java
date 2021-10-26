package fhtw.msc.swe.aigensberger.exercise_12factor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IdentifierDto {

    Long oid;

    @JsonProperty("name")
    String identifier;

    public static IdentifierDto of(BlogEntry blogEntry) {
        return new IdentifierDto(blogEntry.getOid(), blogEntry.getTitle());
    }

    public static IdentifierDto of(Author author) {
        return new IdentifierDto(author.getOid(), getFullName(author));
    }

    private static String getFullName(Author author) {
        return author.getFirstName() + " " + author.getLastName();
    }
}
