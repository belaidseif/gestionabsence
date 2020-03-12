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


import tekup.data.Matiere;
import tekup.repository.MatiereRepository;



@Controller
@RequestMapping("/matiere")
public class MatiereController {
	private MatiereRepository matiereRepo;
	@Autowired
	public MatiereController(MatiereRepository m) {
		matiereRepo = m;
	}
	
	@GetMapping
	 public String getMatieres(Model model) {
		model.addAttribute("matieres", matiereRepo.findAll());
		 return "matiere/matiere";
	 }
	@GetMapping("add")
	 public String addMatieres(Model model) {
		model.addAttribute("matieres", matiereRepo.findAll());
		 return "matiere/addMatiere";
	 }
	@PostMapping("add")
	 public String postMatiere(@Valid Matiere matiere, Errors errors) {
	
		if (errors.hasErrors()) {
			 return "redirect:/matiere";
			 }
		 
		 matiereRepo.save(matiere);
		 return "redirect:/matiere";
	 }
	
	@GetMapping("delete/{id}")
	public String deleteMatiere(@PathVariable Long id) {
		
		matiereRepo.deleteById(id);
		
		return "redirect:/matiere";

	}
	@GetMapping("edit/{id}")
	public String editMatiere(@PathVariable Long id, Model model) {
		model.addAttribute("matiere", matiereRepo.findById(id).get());
		return "matiere/editMat";
	}
	@PostMapping("edit/{id}")
	public String editMatiere(@PathVariable Long id,@Valid Matiere matiere) {
		matiere.setId(id);
		matiereRepo.save(matiere);
		return "redirect:/matiere";
	}
	

}
