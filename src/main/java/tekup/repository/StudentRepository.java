package tekup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.data.Classe;
import tekup.data.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Iterable<Student> findByClasseId(Long id);
}
