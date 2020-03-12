package tekup.web;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tekup.data.Classe;

import tekup.data.Form;
import tekup.data.FormWeekMatiere;
import tekup.data.MarkAbsence;
import tekup.data.Matiere;
import tekup.data.MatiereAbsence;
import tekup.data.Student;
import tekup.data.StudentMatiere;
import tekup.data.Week;
import tekup.data.WeekMatiere;

import tekup.repository.ClasseRepository;
import tekup.repository.MatiereAbsenceRepository;
import tekup.repository.MatiereRepository;
import tekup.repository.StudentRepository;
import tekup.repository.WeekMatiereRepository;


@Controller
@RequestMapping("/classe")
@SessionAttributes
public class ClasseController {
	private Classe  classe ;
	private LocalDate week ;
	@Autowired private WeekMatiereRepository weekRepo;
	@Autowired private MatiereAbsenceRepository maRepo;
	@Autowired private StudentRepository studentRepo;
	@Autowired private MatiereRepository matiereRepo;
	private ClasseRepository classeRepo;
	@Autowired
	public ClasseController(ClasseRepository classeRepo) {
		this.classeRepo = classeRepo;
	}
	
	@GetMapping
	public String getClasses(Model model) {
		model.addAttribute("classes", classeRepo.findAll());
		model.addAttribute("matieres", matiereRepo.findAll());
		 return "classe/classes";
	 }
	@GetMapping("markabs/{id}")
	public String markabs(Model model, @PathVariable Long id) {
		classe = classeRepo.findById(id).get();
		model.addAttribute("students", studentRepo.findByClasseId(id));
		model.addAttribute("weeks", Week.getWeeks());
		model.addAttribute("classe", classe);
		model.addAttribute("markAbsence", new MarkAbsence());
		Iterable<MatiereAbsence> ma = maRepo.findAll();
		List<Long> maList = new ArrayList<>();
		ma.forEach(c->maList.add(c.getWeekMatiere().getId()));
		Map<WeekMatiere, Boolean> map = new HashMap<WeekMatiere, Boolean>();
		if(week!=null) {
			Iterable<WeekMatiere> weeks = weekRepo.findByWeek(week.toString());
			weeks.forEach(w->{
				if(maList.contains(w.getId()))
					map.put(w, true);
				else
					map.put(w, false);
			});
			model.addAttribute("maps",map );
		}
		
		return "classe/markabs";
	}
	
	@PostMapping("/markabs/{id}")
	 public String markabs(@Valid FormWeekMatiere form, Errors errors, @PathVariable Long id) {
		week = form.getWeek().minusDays(1);
		
		if(form.getIds()!=null)
		form.getIds().forEach(a->weekRepo.save(new WeekMatiere(form.getWeek(), matiereRepo.findById(a).get(), classeRepo.findById(id).get())));
		if (errors.hasErrors()) {
			 return "redirect:/classe/classe/edit/"+classe.getId();
			 }
		
		 return "redirect:/classe/markabs/"+classe.getId();
	 }
	
	@PostMapping("postmarkabs/{id}")
	public String postmarkabs(@Valid MarkAbsence markAbsence, Errors errors, @PathVariable Long id, BindingResult result) {
		
		Iterator<WeekMatiere> iter = weekRepo.findByWeekAndClasseId(week.toString(), id.toString()).iterator();
		while(iter.hasNext())
			maRepo.deleteMat(iter.next().getId().toString());
		
		if(markAbsence.getKeys()!=null)
		for(String key : markAbsence.getKeys()) {
			String[] ids = key.split("_");
			
			MatiereAbsence ma = new MatiereAbsence( studentRepo.findById(Long.parseLong(ids[1])).get(), weekRepo.findById(Long.parseLong(ids[0])).get());
			maRepo.save(ma);
			
		}
		
		
		if (errors.hasErrors()) {
			 return "redirect:/classe/classe/edit/"+classe.getId();
			 }
		
		 return "redirect:/classe/"+classe.getId();
	 }
	
	@GetMapping("/{id}")
	public String getClasse(Model model, @PathVariable Long id) {
		classe = classeRepo.findById(id).get();
		Iterator<Student> students = studentRepo.findByClasseId(id).iterator();
		
		List<StudentMatiere> listStudents = new ArrayList<>();
		
		while(students.hasNext()) {
			StudentMatiere sm = new StudentMatiere(students.next(), classe.getListMatieres(), maRepo, LocalDate.now());
			listStudents.add(sm);
		}
		Set<Matiere> matieres = new HashSet<>();
		if(listStudents.size() != 0)
			matieres = listStudents.get(0).getListMatiere().keySet();
		
		model.addAttribute("weeks", Week.getWeeks());
		model.addAttribute("classe", classe);
		model.addAttribute("matieres", matieres);
		model.addAttribute("students", students);
		model.addAttribute("list", listStudents);
		return "classe/classe";
	}
	@PostMapping("/{id}")
	public String getClasse(Model model, @PathVariable Long id, @Valid FormWeekMatiere form) {
		
		classe = classeRepo.findById(id).get();
		Iterator<Student> students = studentRepo.findByClasseId(id).iterator();	
		List<StudentMatiere> listStudents = new ArrayList<>();
		while(students.hasNext()) {
			
			StudentMatiere sm = new StudentMatiere(students.next(), classe.getListMatieres(), maRepo, form.getWeek());
			listStudents.add(sm);
		}
		model.addAttribute("weeks", Week.getWeeks());
		model.addAttribute("classe", classe);
		model.addAttribute("matieres", listStudents.get(0).getListMatiere().keySet());
		model.addAttribute("students", students);
		model.addAttribute("list", listStudents);
		return "classe/classe";
	}
	
	@GetMapping("/add")
	public String addClasses() {
		 return "classe/addClasse";
	 }
	
	@PostMapping("/add")
	 public String postMatiere(@Valid Classe classe, Errors errors) {
		System.out.println("here1");
		if (errors.hasErrors()) {
			 return "redirect:/classe";
			 }
		
		 classeRepo.save(classe);
		 return "redirect:/classe";
	 }
	@GetMapping("edit/{id}")
	public String editClasse(Model model, @PathVariable Long id) {
		classe = classeRepo.findById(id).get();
		model.addAttribute("classe", classe);
		return "classe/oneClasse";
	}
	@GetMapping("/addMatiere/{id}")
	public String addMatiere(Model model, @PathVariable Long id) {
		model.addAttribute("matieres", matiereRepo.findAll());
		model.addAttribute("idClasse", id);
		return "classe/addMatiere";
	}
	
	@PostMapping("/addMatiere/{id}")
	 public String addMatiere(@Valid Form form, Errors errors, @PathVariable Long id) {
		System.out.println(classe.getListMatieres());
		form.getIds().forEach(c->classe.getListMatieres().add(matiereRepo.findById(c).get()));
		classeRepo.save(classe);
		if (errors.hasErrors()) {
			 return "redirect:/classe/classe/edit/"+classe.getId();
			 }
		
		 return "redirect:/classe/edit/"+classe.getId();
	 }
	
	@GetMapping("/delete/{id}")
	public String deleteMatiere(@PathVariable Long id) {
		classe.getListMatieres().remove(matiereRepo.findById(id).get());
		classeRepo.save(classe);
		return "redirect:/classe/"+classe.getId();
	}
	@GetMapping("/remove/{id}")
	public String deleteClasse(@PathVariable Long id) {
		classeRepo.deleteById(id);
		
		return "redirect:/classe";
	}
	
    

}
