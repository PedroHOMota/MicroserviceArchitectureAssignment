package ie.tus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategoryId(Integer integer);
}
