package ie.tus.DTO;

public class AddRecipesToBook {
    int bookId;
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
