package tekup.data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long matricule;
	
	private String nom;
	private String prenom;
	private String email;
	private LocalDate dateNaissance;
	
	@ManyToOne(targetEntity = Classe.class)
	private Classe classe;
	
	public String getNomPrenom() {
		return nom+" "+prenom;
	}
	

}
