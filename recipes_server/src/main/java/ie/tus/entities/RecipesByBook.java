package ie.tus.entities;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "RECIPESBYBOOK")
    public class RecipesByBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_by_book_id")
    private int id;

    @Column(name = "book_id", nullable = false)
    private int bookId;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Recipe> recipes;

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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(final List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
