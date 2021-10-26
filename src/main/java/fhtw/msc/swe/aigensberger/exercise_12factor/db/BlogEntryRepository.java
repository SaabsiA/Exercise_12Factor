package fhtw.msc.swe.aigensberger.exercise_12factor.db;

import fhtw.msc.swe.aigensberger.exercise_12factor.models.BlogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogEntryRepository extends JpaRepository<BlogEntry, Long> {

    boolean existsByTitle(String title);
}
