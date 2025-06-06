package ie.tus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ie.tus.entities.Category;
import ie.tus.entities.Cookbook;

public interface CookbookRepository extends JpaRepository<Cookbook, Integer> {

    List<Cookbook> findByCategory(Category category);
    List<Cookbook> deleteByBookId(int bookId);
}
