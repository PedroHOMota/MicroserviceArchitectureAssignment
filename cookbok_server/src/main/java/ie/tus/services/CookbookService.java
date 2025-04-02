package ie.tus.services;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import ie.tus.entities.Category;
import ie.tus.entities.Cookbook;
import ie.tus.repositories.CategoryRepository;
import ie.tus.repositories.CookbookRepository;

public class CookbookService {
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    CookbookRepository cookbookRepo;

    @Autowired
    private EurekaClient eurekaClient;

    public void createCookBook(Cookbook cookbook){
        cookbookRepo.save(cookbook);
    }

    public HashMap<Integer,String> getAllCookbooks(){
        final HashMap<Integer,String> map = new HashMap<Integer,String>();
        cookbookRepo.findAll().stream().map(cookbook -> map.put(cookbook.getBookId(),cookbook.getName()));

        return map;
    }

//    public HashMap<Integer,String> getAllCategories(){
//        final HashMap<Integer,String> map = new HashMap<Integer,String>();
//        categoryRepo.findAll().stream().map(category -> map.put(category.getcategory_id(),category.getname()));
//
//        return map;
//    }
    public void deleteCookBook(int id){
        cookbookRepo.deleteByBookId(id);
    }

    public void updateCookBook(int id, Cookbook cookbook){
        final Cookbook savedBook = cookbookRepo.findById(id).get();

        savedBook.setName(cookbook.getName());
        //savedBook.setCategory(cookbook.getCategory());

        cookbookRepo.save(savedBook);
    }

//    public void createCategory(Category category){
//        categoryRepo.save(category);
//    }
//
//    public void deleteCategory(int id){
//        categoryRepo.deleteById(id);
//    }
//
//    public void updateCategory(int id, Category cookbook){
//        final Category savedCategory = categoryRepo.findById(id).get();
//
//        savedCategory.setname(cookbook.getname());
//        categoryRepo.save(savedCategory);
//    }


    public void getRecipes(int id){
        final InstanceInfo instanceInfo = eurekaClient.getApplication("").getInstances().stream().findAny().get();
    }
}
