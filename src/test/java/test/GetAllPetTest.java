package test;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetAllPetTest extends BaseTest {

    @Test
    public void getAllPet(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParam("status", "available");
        Response response = requestUtil.getResponse(requestSpecBuilder.build(), "/pet/findByStatus");
        response.print();
    }

    @Test
    public void getSinglePet(){
        Response response = requestUtil.getSingleResponse("0", "/pet/{id}");
        response.prettyPrint();
    }


}
