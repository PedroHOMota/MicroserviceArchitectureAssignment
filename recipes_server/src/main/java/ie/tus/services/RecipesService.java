package ie.tus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.RecipeDTO;
import ie.tus.entities.Recipe;
import ie.tus.entities.RecipesByBook;
import ie.tus.repositories.RecipesByBooksRepository;
import ie.tus.repositories.RecipesRepository;

@Service
public class RecipesService {

    @Autowired
    private RecipesByBooksRepository recipesByBooksRepo;

    @Autowired
    private RecipesRepository recipesRepository;


    public Recipe saveRecipe(RecipeDTO dto){
        Recipe recipe = new Recipe();

        recipe.setRecipe(dto.getRecipe());
        recipe.setAuthor(dto.getAuthor());
        recipe.setTitle(dto.getTitle());
        recipe.setIngredients(dto.getIngredients());
        return recipesRepository.save(recipe);
    }

    public Recipe getRecipe(int id){
        return recipesRepository.findById(id).get();
    }

    public List<Recipe> getAllRecipe(){
        return recipesRepository.findAll();
    }

    public void deleteRecipe(int recipeId){
        recipesRepository.deleteById(recipeId);

    }

    public Recipe updateRecipe(int recipeId, RecipeDTO updated){
        final Recipe recipe = getRecipe(recipeId);
        recipe.setRecipe(updated.getRecipe());
        recipe.setIngredients(updated.getIngredients());
        recipe.setTitle(updated.getTitle());
        recipe.setIngredients(updated.getIngredients());

        return recipesRepository.save(recipe);
    }

    public RecipesByBook saveRecipesToBook(AddRecipesToBook addRecipesToBook){
        List<Recipe> recipes = new ArrayList<>();
        RecipesByBook recipesByBook = new RecipesByBook();

        for (int id : addRecipesToBook.getRecipesIds()){
            final Recipe recipe = getRecipe(id);
            recipes.add(recipe);
        }

        recipesByBook.setRecipes(recipes);
        recipesByBook.setBookId(addRecipesToBook.getBookId());

        return recipesByBooksRepo.save(recipesByBook);
    }

    public RecipesByBook addRecipeToBook(final int bookId, int[] receipesId){
        final RecipesByBook byBookId = recipesByBooksRepo.findByBookId(bookId);

        final List<Recipe> recipes = byBookId.getRecipes();

        for (int i : receipesId) {
            try {
                Recipe recipe = getRecipe(i);

                if (!byBookId.getRecipes().contains(recipe)) {
                    byBookId.getRecipes().add(getRecipe(i));
                }
            } catch (Exception e) {
                System.out.println("Recipe "+i+" doesnt exist");
            }
        }

        return recipesByBooksRepo.save(byBookId);
    }

    public RecipesByBook getRecipesBook(int bookId){
        return recipesByBooksRepo.findByBookId(bookId);
    }

    public void deleteRecipesBook(int bookId){
        recipesByBooksRepo.deleteByBookId(bookId);
    }
}
