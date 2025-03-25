package ie.tus.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Cookbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Category> category;

    private String name;


    public String getName() {
        return this.name;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int value) {
        this.bookId = value;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(final List<Category> category) {
        this.category = category;
    }
}
