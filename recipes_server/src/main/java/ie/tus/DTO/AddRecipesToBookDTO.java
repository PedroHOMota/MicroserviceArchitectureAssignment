package ie.tus.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import reactor.util.annotation.NonNull;

public class AddRecipesToBookDTO {
    @Min(1)
    int bookId;

    @NotNull
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
