package tekup.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tekup.data.MatiereAbsence;

@Repository
@Transactional
public interface MatiereAbsenceRepository extends JpaRepository<MatiereAbsence, Long> {
	@Query(value = "select count(*) from week_matiere w, matiere_absence m where m.week_matiere_id = w.id and m.student_matricule = ?1 and "
			+ "w.matiere_id = ?2 and w.week <= ?3", nativeQuery = true)
	Long getNombreHeure(String ids, String idm, String date);
	@Modifying
	@Query(value = "DELETE FROM matiere_absence m WHERE m.week_matiere_id = :id", nativeQuery = true)
	int deleteMat(@Param("id") String id);
	
}
