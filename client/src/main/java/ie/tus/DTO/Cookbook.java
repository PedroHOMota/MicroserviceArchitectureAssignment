package ie.tus.DTO;

import java.util.List;

public class Cookbook {
    private int bookId;

    private String name;
    private List<Category> category;



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
