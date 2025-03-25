package ie.tus.services;

import org.springframework.beans.factory.annotation.Autowired;

import ie.tus.entities.Category;
import ie.tus.entities.Cookbook;
import ie.tus.repositories.CategoryRepository;
import ie.tus.repositories.CookbookRepository;

public class CookbookService {
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    CookbookRepository cookbookRepo;

    public void createCookBook(Cookbook cookbook){
        cookbookRepo.save(cookbook);
    }

    public void deleteCookBook(int id){
        cookbookRepo.deleteByBookId(id);
        //TODO onDelete send message to other service to remove too
    }

    public void updateCookBook(int id, Cookbook cookbook){
        final Cookbook savedBook = cookbookRepo.findById(id).get();

        savedBook.setName(cookbook.getName());
        savedBook.setCategory(cookbook.getCategory());

        cookbookRepo.save(savedBook);
    }

    public void createCategory(Category category){
        categoryRepo.save(category);
    }

    public void deleteCategory(int id){
        categoryRepo.deleteById(id);
    }

    public void updateCategory(int id, Category cookbook){
        final Category savedCategory = categoryRepo.findById(id).get();

        savedCategory.setname(cookbook.getname());
        categoryRepo.save(savedCategory);
    }
}
