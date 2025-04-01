package ie.tus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
