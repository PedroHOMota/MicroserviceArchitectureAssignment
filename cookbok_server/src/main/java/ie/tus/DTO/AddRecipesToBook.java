package ie.tus.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AddRecipesToBook {
    @NotBlank
    int bookId;
    @NotEmpty
    int[] recipesIds;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(final int bookId) {
        this.bookId = bookId;
    }

    public int[] getRecipesIds() {
        return recipesIds;
    }

    public void setRecipesIds(final int[] recipesIds) {
        this.recipesIds = recipesIds;
    }
}
