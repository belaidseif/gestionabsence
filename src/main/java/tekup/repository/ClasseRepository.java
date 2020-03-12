package tekup.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import tekup.data.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
	
}
