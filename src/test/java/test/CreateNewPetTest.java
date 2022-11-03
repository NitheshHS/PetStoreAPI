package test;

import base.BaseTest;
import base.IEndPoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import requestpojo.Category;
import requestpojo.CreatePet;
import requestpojo.Tag;

public class CreateNewPetTest extends BaseTest {

    @Test
    public void createPet(){
        //category
        Category category=new Category();
        category.setId(1);
        category.setName("Animal");

        //Tag
        Tag tag1=new Tag();
        tag1.setId(1);
        tag1.setName("No tags");

        //photoUrl
        String[] photoUrl= {"https://pet.com"};

        CreatePet pet = new CreatePet();
        pet.setId(1);
        pet.setCategory(category);
        pet.setName("PitBull");
        pet.setTags(new Tag[]{tag1});
        pet.setPhotoUrls(photoUrl);
        pet.setStatus("Available");

        Response response = requestUtil.postResponse(pet, IEndPoints.ADD_NEW_PET);
        responseUtil.printResponse(response);
        responseUtil.verifyStatusCode(200);
        responseUtil.verifyContentTypeIsJson();

    }
}
