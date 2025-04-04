package ie.tus.DTO;

import java.util.List;

public class RecipesByBookDTO {

    private int id;

    private int bookId;

    private List<RecipeDTO> recipes;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(final int bookId) {
        this.bookId = bookId;
    }

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(final List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }
}
