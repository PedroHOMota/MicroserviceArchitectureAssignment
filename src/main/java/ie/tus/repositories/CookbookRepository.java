package ie.tus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.entities.Cookbook;
import ie.tus.entities.RecipesByBook;

public interface CookbookRepository extends JpaRepository<Cookbook, Integer> {

    List<Cookbook> findByCategoryId(int category_id);
    List<Cookbook> deleteByBookId(int bookId);
}
