package ie.tus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.entities.Recipe;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {
}
