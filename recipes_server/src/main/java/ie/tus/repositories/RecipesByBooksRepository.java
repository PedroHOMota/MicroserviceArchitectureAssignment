package ie.tus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.entities.RecipesByBook;

public interface RecipesByBooksRepository extends JpaRepository<RecipesByBook, Integer> {

    RecipesByBook findByBookId(int bookId);
    void deleteByBookId(int bookId);

}
