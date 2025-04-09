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

package ie.tus.DTO;

import java.util.ArrayList;

import jakarta.validation.constraints.NotBlank;

public class CookbookDTO {
    @NotBlank
    private String name;

    private ArrayList<Integer> categories;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<Integer> getCategories() {
        return categories;
    }

    public void setCategories(final ArrayList<Integer> categories) {
        this.categories = categories;
    }
}
