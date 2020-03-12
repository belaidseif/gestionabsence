package tekup.data;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;


import lombok.Data;
import tekup.repository.MatiereAbsenceRepository;
@Data
public class StudentMatiere {
	
	private Student student;
	
	private Map<Matiere, Double> listMatiere = new HashMap<>();
	public StudentMatiere(Student student, Set<Matiere> matieres, MatiereAbsenceRepository maRepo, LocalDate week) {
		this.student = student;
		for(Matiere matiere : matieres) {
			Long nbr = maRepo.getNombreHeure(student.getMatricule().toString(), matiere.getId().toString(), week.toString());
			listMatiere.put(matiere, nbr*1.5);
		}
		//matieres.forEach(m->listMatiere.put(m, maRepo.findByStudentMatriculeAndMatiereId(student.getMatricule(), m.getId()).orElse(new MatiereAbsence())));
		
	}

}
