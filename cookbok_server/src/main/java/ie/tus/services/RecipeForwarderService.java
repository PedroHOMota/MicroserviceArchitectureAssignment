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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.netflix.discovery.EurekaClient;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.RecipeDTO;
import ie.tus.DTO.RecipesByBookDTO;
import ie.tus.util.PerformRest;
import jakarta.inject.Inject;

@Service
public class RecipeForwarderService {

    private final EurekaClient eurekaClient;

    private final PerformRest restClient;

    private final StringBuilder urlBuilder = new StringBuilder();

    @Inject
    public RecipeForwarderService(final EurekaClient client, final PerformRest restClient){
        this.eurekaClient = client;
        this.restClient = restClient;
    }

    public RecipeDTO saveRecipe(RecipeDTO dto){
        urlBuilder.setLength(0);
        urlBuilder.append(restClient.getBaseUrl("RECIPES")).append("/recipes");

        System.out.println("\nURL: "+urlBuilder.toString());

        return (RecipeDTO) restClient.performPost(urlBuilder.toString(),dto,RecipeDTO.class);
    }

    public RecipeDTO getRecipe(int id){
        urlBuilder.setLength(0);
        urlBuilder.append(restClient.getBaseUrl("RECIPES")).append("/recipes/").append(id);

        System.out.println("\nURL: "+urlBuilder.toString());

        return (RecipeDTO) restClient.performGet(urlBuilder.toString(),RecipeDTO.class);
    }

    public List<RecipeDTO> getAllRecipes(){
        urlBuilder.setLength(0);
        urlBuilder.append(restClient.getBaseUrl("RECIPES")).append("/recipes/all");

        System.out.println("\nURL: "+urlBuilder.toString());

        return (List<RecipeDTO>) restClient.performGet(urlBuilder.toString(),List.class);
    }

    public void deleteRecipeDTO(int recipeId){
        // /recipes/{id}
    }

    public RecipeDTO updateRecipe(int id, RecipeDTO dto){
        urlBuilder.setLength(0);
        urlBuilder.append(restClient.getBaseUrl("RECIPES")).append("/recipes/").append(id);

        System.out.println("\nURL: "+urlBuilder.toString());

        return (RecipeDTO) restClient.performPut(urlBuilder.toString(),dto,RecipeDTO.class);
    }
}
