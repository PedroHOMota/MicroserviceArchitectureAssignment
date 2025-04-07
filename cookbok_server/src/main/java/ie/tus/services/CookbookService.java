package ie.tus.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.EurekaClient;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.util.entities.Cookbook;
import ie.tus.DTO.RecipesByBookDTO;
import ie.tus.repositories.CategoryRepository;
import ie.tus.repositories.CookbookRepository;
import ie.tus.util.PerformRest;
import jakarta.inject.Inject;

@Service
public class CookbookService {
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    CookbookRepository cookbookRepo;

    @Autowired
    private EurekaClient eurekaClient;

    final StringBuilder urlBuilder = new StringBuilder();

    final PerformRest restClient;

    @Inject
    public CookbookService(final PerformRest restClient){
        this.restClient = restClient;
    }

    public Cookbook createCookBook(String cookbookTitle){
        final Cookbook cookbook = new Cookbook();
        cookbook.setName(cookbookTitle);
        return cookbookRepo.save(cookbook);
    }

    public HashMap<Integer,String> getAllCookbooks(){
        final HashMap<Integer,String> map = new HashMap<Integer,String>();
        List<Cookbook> all = cookbookRepo.findAll();
        all.forEach(cookbook -> {
            map.put(cookbook.getBookId(),cookbook.getName());
        });
        return map;
    }

    public List<Cookbook> getAllCookbookS(){

        return cookbookRepo.findAll();
    }

    public Cookbook getCookBookById(int id){

        return cookbookRepo.findById(id).get();
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

    public Cookbook updateCookBook(int id, String cookbookTitle){
        final Cookbook savedBook = cookbookRepo.findById(id).get();

        savedBook.setName(cookbookTitle);

        return cookbookRepo.save(savedBook);
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


    public RecipesByBookDTO getRecipesForBook(int id){
        urlBuilder.setLength(0);
        urlBuilder.append(restClient.getBaseUrl("RECIPES")).append("/recipes/byBook/").append(id);


        System.out.println("\nURL: "+urlBuilder.toString());

        return (RecipesByBookDTO) restClient.performGet(urlBuilder.toString(),RecipesByBookDTO.class);
    }


    public RecipesByBookDTO saveRecipesToBook(int id, AddRecipesToBook recipes){
        urlBuilder.setLength(0);
        urlBuilder.append(restClient.getBaseUrl("RECIPES")).append("/recipes/byBook");

        System.out.println("\nURL: "+urlBuilder.toString());

        return (RecipesByBookDTO) restClient.performPost(urlBuilder.toString(), recipes,RecipesByBookDTO.class);
    }

}
