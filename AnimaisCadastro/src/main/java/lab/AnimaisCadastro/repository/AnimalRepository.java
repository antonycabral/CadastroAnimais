package lab.AnimaisCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.AnimaisCadastro.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{

}
