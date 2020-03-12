package tekup.web;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tekup.data.Student;
import tekup.repository.ClasseRepository;
import tekup.repository.StudentRepository;

@Controller
@RequestMapping("student")
public class StudentController {
	private ClasseRepository classeRepo;
	private StudentRepository studentRepo;
	@Autowired
	public StudentController(ClasseRepository classeRepo, StudentRepository studentRepo) {
		this.classeRepo = classeRepo;
		this.studentRepo = studentRepo;
	}
	@GetMapping
	public String getStudents(Model model) {
		model.addAttribute("students", studentRepo.findAll());
		return "student/student";
	}
	@GetMapping("add")
	public String addStudent(Model model) {
		model.addAttribute("classes", classeRepo.findAll());
		return "student/addStudent";
	}
	@PostMapping("add")
	public String addStudent(@Valid Student student, Errors errors) {
		if (errors.hasErrors()) {		
			System.out.println(errors);
			 return "redirect:/student";
		}
		studentRepo.save(student);
		return "redirect:/student";
	}
	@GetMapping("edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentRepo.findById(id).get());
		model.addAttribute("classes", classeRepo.findAll());
		return "student/editStudent";
	}
	@PostMapping("edit/{id}")
	public String editStudent(@Valid Student student, Errors errors, @PathVariable Long id) {
		student.setMatricule(id);
		if (errors.hasErrors()) {		
			System.out.println(errors);
			 return "redirect:/student";
		}
		studentRepo.save(student);
		return "redirect:/student";
	}
	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentRepo.deleteById(id);
		return "redirect:/student";
	}
}
