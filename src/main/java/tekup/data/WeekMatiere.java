package tekup.data;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WeekMatiere {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDate week;
	
	@ManyToOne(targetEntity = Matiere.class)
	private Matiere matiere;
	
	@ManyToOne(targetEntity = Classe.class)
	private Classe classe;
	
	public WeekMatiere(LocalDate w, Matiere m, Classe c) {
		week = w;
		matiere = m;
		classe = c;
	}
}
