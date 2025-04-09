/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2025
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package ie.tus.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.tus.entities.Category;
import ie.tus.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepo;

    public Category createCategory(String categoryName) {
        Category category = new Category();
        category.setname(categoryName);
        return categoryRepo.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }

    public Category updateCategory(int id, String categoryName) {
        final Category savedCategory = categoryRepo.findById(id).get();

        savedCategory.setname(categoryName);
        return categoryRepo.save(savedCategory);
    }

    public HashMap<Integer, String> getAllCategories() {
        final HashMap<Integer, String> map = new HashMap<Integer, String>();
        final List<Category> all = categoryRepo.findAll();

        all.forEach(category -> {
            map.put(category.getcategory_id(), category.getname());
        });

        return map;
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findByCategoryId(id).get();
    }
}
