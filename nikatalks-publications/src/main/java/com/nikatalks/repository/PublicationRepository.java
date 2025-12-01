package com.nikatalks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikatalks.commons.entity.FileDoc;
import com.nikatalks.commons.entity.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long>{

	@Query("SELECT p FROM Publication p WHERE p.language = :language")
    List<Publication> findByLanguage(String language);

    @Query("SELECT p FROM Publication p WHERE p.level = :level")
    List<Publication> findByLevel(String level);

    @Query("SELECT p FROM Publication p WHERE p.competency = :competency")
    List<Publication> findByCompetency(String competency);
    
    @Query("""
    	       SELECT p
    	       FROM Publication p
    	       WHERE (p.destination IS NULL OR p.destination = '' OR p.destination = :destination)
    	         AND (p.level IS NULL OR p.level = '' OR p.level = :level)
    	         AND (p.competency IS NULL OR p.competency = '' OR p.competency = :competency)
    	         AND (p.language = :language)
    	       """)
    	List<Publication> findByLanguageDestinationLevelOrCompetency(
    			@Param("language") String language,
			    @Param("destination") String destination,
			    @Param("competency") String competency,
			    @Param("level") String level
    	);

}
