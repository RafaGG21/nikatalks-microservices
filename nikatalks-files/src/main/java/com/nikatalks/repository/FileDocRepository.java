package com.nikatalks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nikatalks.commons.entity.FileDoc;

public interface FileDocRepository extends JpaRepository<FileDoc, Long>{
	
	@Query("SELECT f FROM FileDoc f WHERE f.language = :language")
	List<FileDoc> findByLanguage(@Param("language") String language);

	@Query("SELECT f FROM FileDoc f WHERE f.competence = :competence")
	List<FileDoc> findBycompetence(@Param("competence") String competence);

	@Query("SELECT f FROM FileDoc f WHERE f.language = :language and f.competence = :competence")
	List<FileDoc> findByLanguageAndCompetence(@Param("language") String language,
			@Param("competence") String competence);
	
	@Query("SELECT f FROM FileDoc f WHERE f.language = :language and f.competence = :competence and f.destination = :destination ")
	List<FileDoc> findByLanguageCompetenceAndDestination(@Param("language") String language,
			@Param("competence") String competence,
			@Param("destination") String destination);
	
	@Query("""
		    SELECT f FROM FileDoc f
		    WHERE (f.language = :language)
		      AND (:destination IS NULL OR :destination = '' OR f.destination = :destination)
		      AND (:competence IS NULL OR :competence = '' OR f.competence = :competence)
		      AND (:level IS NULL OR :level = '' OR f.level = :level)
		""")
		List<FileDoc> findByLanguageDestinationLevelOrCompetence(
		    @Param("language") String language,
		    @Param("destination") String destination,
		    @Param("competence") String competence,
		    @Param("level") String level
		);


}
