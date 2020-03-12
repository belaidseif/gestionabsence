package tekup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.data.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
	
}
