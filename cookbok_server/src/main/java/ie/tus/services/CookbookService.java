package ie.tus.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.EurekaClient;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.CookbookDTO;
import ie.tus.entities.Category;
import ie.tus.entities.Cookbook;
import ie.tus.DTO.RecipesByBookDTO;
import ie.tus.repositories.CategoryRepository;
import ie.tus.repositories.CookbookRepository;
import ie.tus.util.PerformRest;
import jakarta.inject.Inject;

@Service
public class CookbookService {
    @Autowired
    private CookbookRepository cookbookRepo;

    private CategoryService categoryService;

    final StringBuilder urlBuilder = new StringBuilder();

    final PerformRest restClient;

    @Inject
    public CookbookService(final PerformRest restClient, final CategoryService categoryService) {
        this.restClient = restClient;
        this.categoryService = categoryService;
    }

    public Cookbook createCookBook(final CookbookDTO cookbookDTO){
        final Cookbook cookbook = new Cookbook();
        cookbook.setName(cookbookDTO.getName());

        LinkedList<Category> categories = new LinkedList<>();
        for (final int id : cookbookDTO.getCategories()) {
            categories.add(categoryService.getCategoryById(id));
        }

        cookbook.setCategory(categories);
        final Cookbook save = cookbookRepo.save(cookbook);
        return save;
    }

    public HashMap<Integer,String> getAllCookbooks(){
        final HashMap<Integer,String> map = new HashMap<Integer,String>();
        List<Cookbook> all = cookbookRepo.findAll();
        all.forEach(cookbook -> {
            map.put(cookbook.getBookId(),cookbook.getName());
        });
        return map;
    }

    public List<Cookbook> getAllCookbooksL(){
        List<Cookbook> all = cookbookRepo.findAll();


        return all;
    }


    public List<Cookbook> getAllCookbookS(){

        return cookbookRepo.findAll();
    }

    public Cookbook getCookBookById(int id){

        return cookbookRepo.findById(id).get();
    }


    public void deleteCookBook(int id){
        cookbookRepo.deleteByBookId(id);
    }

    public Cookbook updateCookBook(int id, CookbookDTO cookbookDTO){
        final Cookbook savedBook = cookbookRepo.findById(id).get();

        savedBook.setName(cookbookDTO.getName());
        savedBook.getCategory().forEach(category -> {
            cookbookDTO.getCategories().removeIf(cId -> {
                return cId.equals(category.getcategory_id());
            });
        });

        cookbookDTO.getCategories().forEach(categoryId -> {
            savedBook.getCategory().add(categoryService.getCategoryById(categoryId));
        });

        return cookbookRepo.save(savedBook);
    }

    public Cookbook removeCategories(int id, CookbookDTO cookbookDTO){
        final Cookbook savedBook = cookbookRepo.findById(id).get();

        savedBook.getCategory().removeIf(category -> {
            return cookbookDTO.getCategories().contains(category);
        });

        return cookbookRepo.save(savedBook);
    }

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
