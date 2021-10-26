package fhtw.msc.swe.aigensberger.exercise_12factor.db;

import fhtw.msc.swe.aigensberger.exercise_12factor.models.Author;
import fhtw.msc.swe.aigensberger.exercise_12factor.models.BlogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DBInitializer {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BlogEntryRepository blogEntryRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady() {
        final List<Author> authors = List.of(
                new Author("Gray", "Fullbuster"),
                new Author("Erza", "Scarlet"),
                new Author("Mirajane", "Strauss"),
                new Author("Lucy", "Heartfilia"),
                new Author("Natsu", "Dragneel")
        );

        var newAuthors = authors.stream()
                .filter(author -> !authorRepository.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());

        authorRepository.saveAll(newAuthors);

        final List<Author> existingAuthors = authorRepository.findAll();

        final List<BlogEntry> blogEntries = new ArrayList<>();
        for (Author author : existingAuthors) {
            final String title = "Demo Title " + author.getFirstName();
            blogEntries.add(new BlogEntry(title, "This is just an example blog entry", LocalDate.now(), author));
        }

        var newBlogEntries = blogEntries.stream()
                .filter(blogEntry -> !blogEntryRepository.existsByTitle(blogEntry.getTitle()))
                .collect(Collectors.toList());

        blogEntryRepository.saveAll(newBlogEntries);
    }
}
