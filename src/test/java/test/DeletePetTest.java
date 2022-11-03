package test;

import base.BaseTest;
import base.IEndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import requestpojo.Category;
import requestpojo.CreatePet;
import requestpojo.Tag;

public class DeletePetTest extends BaseTest {

    @Test
    public void deletePet(){
        Category  category = new Category(1, "Animal");
        Tag[] tag={new Tag(1,"No awards")};
        String[] photourls = {"https://mypet.com"};
        CreatePet createPetPayload = new CreatePet(1, category, "Dog", photourls, tag, "available");
        Response response = requestUtil.postResponse(createPetPayload, IEndPoints.ADD_NEW_PET);
        Integer id = (Integer) responseUtil.getJsonPathValue(response, "id");
        responseUtil.printResponse(response);

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addPathParam("petId", id);

        response=requestUtil.deleteRequest(requestSpecBuilder.build(), IEndPoints.DELETE_PET);
        responseUtil.printResponse(response);

    }
}
