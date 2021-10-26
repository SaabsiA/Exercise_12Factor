package fhtw.msc.swe.aigensberger.exercise_12factor.controllers;

import fhtw.msc.swe.aigensberger.exercise_12factor.db.AuthorRepository;
import fhtw.msc.swe.aigensberger.exercise_12factor.db.BlogEntryRepository;
import fhtw.msc.swe.aigensberger.exercise_12factor.models.BlogEntry;
import fhtw.msc.swe.aigensberger.exercise_12factor.models.BlogEntryDto;
import fhtw.msc.swe.aigensberger.exercise_12factor.models.BlogEntryListItemDto;
import fhtw.msc.swe.aigensberger.exercise_12factor.models.IdentifierDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogentries")
@CrossOrigin
@Log
public class BlogEntryController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BlogEntryRepository blogEntryRepository;

    @GetMapping("/{oid}")
    public ResponseEntity<BlogEntryDto> getById(@PathVariable long oid) {
        log.info("Fetch Blog Entry with id " + oid + " from DB");

        Optional<BlogEntry> blogEntry = Optional.empty();

        try {
            blogEntry = blogEntryRepository.findById(oid);
        } catch (Exception exc) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            log.severe("Exception while fetching Blog Entry with id" + oid);
        }

        return blogEntry.map(BlogEntryDto::of)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EmptyResultDataAccessException("Can't find blog with id " + oid, 1));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BlogEntry blogEntry) {
        log.info("Create a new Blog Entry");

        blogEntry.setOid(null);

        blogEntry = blogEntryRepository.save(blogEntry);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).getById(blogEntry.getOid())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<BlogEntryDto> getAll() {
        log.info("Fetch all Blog Entries from the DB");

        return blogEntryRepository.findAll().stream()
                .map(BlogEntryDto::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/listitems")
    public List<BlogEntryListItemDto> getAllBlogEntryListItems() {
        log.info("Fetch all Blog Entries as List Items from the DB");

        return blogEntryRepository.findAll(
                        Sort.by(
                                Sort.Order.desc("title")
                        )
                ).stream()
                .map(BlogEntryListItemDto::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/identifiers")
    public List<IdentifierDto> getAllBlogEntryIdentifiers() {
        log.info("Fetch Identifiers for all Blog Entries from the DB");

        return blogEntryRepository.findAll()
                .stream()
                .map(IdentifierDto::of)
                .collect(Collectors.toList());
    }
}
