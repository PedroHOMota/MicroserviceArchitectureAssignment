package ie.tus.DTO;

public class RecipeDTO {
    private int recipeId;

    private String recipe;
    private String ingredients;
    private String author;
    private String title;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(final int recipeId) {
        this.recipeId = recipeId;
    }

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
