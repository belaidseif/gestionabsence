package tekup.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tekup.data.WeekMatiere;

public interface WeekMatiereRepository extends JpaRepository<WeekMatiere, Long>{

	@Query(value = "select * from week_matiere u where u.week = ?1", nativeQuery = true)
	Iterable<WeekMatiere> findByWeek(String string);
	@Query(value = "select * from week_matiere u where u.week = ?1 and u.classe_id = ?2", nativeQuery = true)
	Iterable<WeekMatiere> findByWeekAndClasseId(String week, String id);

}
