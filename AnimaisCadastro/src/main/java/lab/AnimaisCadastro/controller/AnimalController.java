package lab.AnimaisCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.AnimaisCadastro.model.Animal;
import lab.AnimaisCadastro.repository.AnimalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    public String listarAnimais(Model model) {
        model.addAttribute("animais", animalRepository.findAll());
        return "animais/list";
    }

    @GetMapping("/novo")
    public String novoAnimalForm(Model model){
        model.addAttribute("animal", new Animal());
        return "animais/form";
    }

    @PostMapping
    public String salvarAnimal(@ModelAttribute Animal animal){
        animalRepository.save(animal);
        return "redirect:/animais";
    }

    @GetMapping("/{id}")
    public String detalhesAnimal(@PathVariable Long id, Model model) {
        // Busca o animal pelo ID, ou lança uma exceção se o ID for inválido
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        // Adiciona o animal ao modelo para ser usado no template Thymeleaf
        model.addAttribute("animal", animal);

        // Retorna o template de detalhes
        return "animais/detalhes";
    }
    
    @GetMapping("/{id}/editar")
    public String editarAnimal(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id)));
        return "animais/form";
    }
    
    @GetMapping("/{id}/deletar")
    public String deletarAnimal(@PathVariable Long id) {
        animalRepository.deleteById(id);
        return "redirect:/animais";
    }
    
}
