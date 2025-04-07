package ie.tus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.util.entities.Cookbook;

public interface CookbookRepository extends JpaRepository<Cookbook, Integer> {

    //List<Cookbook> findByCategoryId(int categoryId);
    List<Cookbook> deleteByBookId(int bookId);
}
