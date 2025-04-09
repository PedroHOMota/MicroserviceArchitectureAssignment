package ie.tus.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "CATEGORY")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @NotBlank(message = "Please provide a category name")
    private String name;

    public int getcategory_id() {
        return this.categoryId;
    }

    public void setcategory_id(int value) {
        this.categoryId = value;
    }
    public String getname() {
        return this.name;
    }
    public void setname(String value) {
        this.name = value;
    }

}
