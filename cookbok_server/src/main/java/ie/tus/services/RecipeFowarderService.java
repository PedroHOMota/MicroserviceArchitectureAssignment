///*------------------------------------------------------------------------------
// *******************************************************************************
// * COPYRIGHT Ericsson 2025
// *
// * The copyright to the computer program(s) herein is the property of
// * Ericsson Inc. The programs may be used and/or copied only with written
// * permission from Ericsson Inc. or in accordance with the terms and
// * conditions stipulated in the agreement/contract under which the
// * program(s) have been supplied.
// *******************************************************************************
// *----------------------------------------------------------------------------*/
//
//package ie.tus.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.client.RestClient;
//
//import com.netflix.discovery.EurekaClient;
//
//import ie.tus.DTO.AddRecipeEntitysToBook;
//import ie.tus.DTO.RecipeEntityDTO;
//import ie.tus.entities.RecipeEntity;
//import ie.tus.entities.RecipeEntitysByBook;
//
//public class RecipeFowarderService {
//
//    @Autowired
//    private EurekaClient eurekaClient;
//
//    final RestClient restClient = RestClient.builder().build();
//
//
//    public RecipeEntity saveRecipeEntity(RecipeEntityDTO dto){
//
//    }
//
//    public RecipeEntity getRecipeEntity(int id){
//    }
//
//    public List<RecipeEntity> getAllRecipeEntity(){
//    }
//
//    public void deleteRecipeEntity(int recipeId){
//
//    }
//
//    public RecipeEntity updateRecipeEntity(int recipeId, RecipeEntityDTO updated){
//
//    }
//
//    public RecipeEntitysByBook saveRecipeEntitysToBook(AddRecipeEntitysToBook addRecipeEntitysToBook){
//
//    }
//
//    public RecipeEntitysByBook addRecipeEntityToBook(final int bookId, int[] receipesId){
//
//    }
//
//    public RecipeEntitysByBook getRecipeEntitysBook(int bookId){
//    }
//
//    public void deleteRecipeEntitysBook(int bookId){
//    }
//}
