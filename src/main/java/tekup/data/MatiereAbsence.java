package tekup.data;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.mail.javamail.JavaMailSender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MatiereAbsence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne(targetEntity = Student.class)
	private Student student;
	
	@ManyToOne(targetEntity = WeekMatiere.class)
	private WeekMatiere weekMatiere;
	 public MatiereAbsence(Student s, WeekMatiere w) {
		 student =s;
		 weekMatiere =w;
	 }
	
	
	

}
