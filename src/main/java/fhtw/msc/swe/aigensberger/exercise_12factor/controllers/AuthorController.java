package fhtw.msc.swe.aigensberger.exercise_12factor.controllers;

import fhtw.msc.swe.aigensberger.exercise_12factor.db.AuthorRepository;
import fhtw.msc.swe.aigensberger.exercise_12factor.models.Author;
import fhtw.msc.swe.aigensberger.exercise_12factor.models.IdentifierDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@CrossOrigin
@Log
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAllAuthors() {
        log.info("Fetching all Authors from the DB");

        return new ArrayList<>(authorRepository.findAll(
                Sort.by(
                        Sort.Order.desc("lastName")
                )
        ));
    }

    @GetMapping("/identifiers")
    public List<IdentifierDto> getAllAuthorIdentifiers() {
        log.info("Fetching Identifiers for all Authors in the DB");

        return authorRepository.findAll().stream()
                .map(IdentifierDto::of)
                .collect(Collectors.toList());
    }
}
