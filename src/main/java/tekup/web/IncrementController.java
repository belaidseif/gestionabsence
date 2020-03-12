package tekup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tekup.data.MatiereAbsence;
import tekup.repository.MatiereAbsenceRepository;
import tekup.repository.MatiereRepository;
import tekup.repository.StudentRepository;

@Controller
@RequestMapping("increment")
public class IncrementController {/*
	@Autowired private MatiereAbsenceRepository maRepo;
	@Autowired private MatiereRepository matiereRepo;
	@Autowired private StudentRepository studentRepo;
	@Autowired private JavaMailSender jmSender;
	@GetMapping("{ids}/{idm}/{idc}")
	public String increment(@PathVariable("ids") Long ids, @PathVariable("idm") Long idm, @PathVariable("idc") Long idc) {
		MatiereAbsence ma = maRepo.findByStudentMatriculeAndMatiereId(ids, idm).orElse(new MatiereAbsence(studentRepo.findById(ids).get(), matiereRepo.findById(idm).get()));
		
		ma.increment(jmSender);
		
		maRepo.save(ma);
		
		return "redirect:/classe/"+idc;
	}
	@GetMapping("dec/{ids}/{idm}/{idc}")
	public String decrement(@PathVariable("ids") Long ids, @PathVariable("idm") Long idm, @PathVariable("idc") Long idc) {
		MatiereAbsence ma = maRepo.findByStudentMatriculeAndMatiereId(ids, idm).orElse(new MatiereAbsence(studentRepo.findById(ids).get(), matiereRepo.findById(idm).get()));
		
		ma.decrement();
		
		maRepo.save(ma);
		return "redirect:/classe/"+idc;
	}*/
}
