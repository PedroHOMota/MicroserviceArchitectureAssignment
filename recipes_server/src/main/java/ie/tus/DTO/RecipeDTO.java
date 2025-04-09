package ie.tus.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RecipeDTO{
    private String recipe;
    private String ingredients;
    @NotBlank(message = "Author cant be empty")
    @Pattern(regexp = "[a-zA-Z ]")
    private String author;
    private String title;

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(final String recipe) {
        this.recipe = recipe;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(final String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
