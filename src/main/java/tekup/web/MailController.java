package tekup.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NonNull;
import tekup.data.Matiere;
import tekup.data.Student;
import tekup.repository.MatiereAbsenceRepository;
import tekup.repository.StudentRepository;

@Component
@Controller
public class MailController {
	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	MatiereAbsenceRepository maRepo;
	
	@Scheduled(cron = "0 30 9 ? * FRI")
    @GetMapping("/sendEmail")
    public String sendSimpleEmail() {
    	
    	Iterable<Student> students = studentRepo.findAll();
    	
    	for (Student student : students) {
    		@NonNull
			String StudentMail = student.getEmail();
    		String studentName="Bonjour  "+student.getNomPrenom();
    		for(Matiere matiere :student.getClasse().getListMatieres()) {
    			Double nbrHeures = maRepo.getNombreHeure(student.getMatricule().toString(), matiere.getId().toString(), LocalDate.now().toString())*1.5;
    			if(nbrHeures > matiere.getNbrHeure()*0.36) {
    				SimpleMailMessage message = new SimpleMailMessage();
    		         
    		        message.setTo("belaidseif@gmail.com");
    		        message.setSubject("La liste D'absence");
    		        message.setText(studentName+" tu es éliminé dans la matiere "+matiere.getLabel());  		 
    		        this.emailSender.send(message);
    			}
    		}
    	
        
        
    	}
        return "home";
    }
    
}
