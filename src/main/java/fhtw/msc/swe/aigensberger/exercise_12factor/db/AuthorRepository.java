package fhtw.msc.swe.aigensberger.exercise_12factor.db;

import fhtw.msc.swe.aigensberger.exercise_12factor.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
