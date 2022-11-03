package test;

import static File.JsonFileUtil.*;

import File.JsonFilePath;
import base.BaseTest;
import base.IEndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import requestpojo.Category;
import requestpojo.CreatePet;
import requestpojo.Tag;

public class GetAllPetTest extends BaseTest {

    @Test
    public void getAllPetByAvailableStatus() throws Exception {
        String json = loadJsonFile(JsonFilePath.GET_TESTDATA);
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParam("status", String.valueOf(read(json,"$.getAllAvailablePet.status")));
        Response response = requestUtil.getResponse(requestSpecBuilder.build(), IEndPoints.FIND_PET_BY_STATUS);
        response.print();
    }

    @Test
    public void getAllPetByPendingStatus() throws Exception {
        String json = loadJsonFile(JsonFilePath.GET_TESTDATA);
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParam("status", String.valueOf(read(json,"$.getAllPendingPet.status")));
        Response response = requestUtil.getResponse(requestSpecBuilder.build(), IEndPoints.FIND_PET_BY_STATUS);
        response.print();
    }

    @Test
    public void getAllPetBySoldStatus() throws Exception {
        String json = loadJsonFile(JsonFilePath.GET_TESTDATA);
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParam("status", String.valueOf(read(json,"$.getAllSoldPet.status")));
        Response response = requestUtil.getResponse(requestSpecBuilder.build(), IEndPoints.FIND_PET_BY_STATUS);
        response.print();
    }

    @Test
    public void getSinglePet(){
        Category category = new Category(1, "Animal");
        Tag[] tag={new Tag(1,"No awards")};
        String[] photourls = {"https://mypet.com"};
        CreatePet createPetPayload = new CreatePet(1, category, "Dog", photourls, tag, "available");
        Response response = requestUtil.postResponse(createPetPayload, IEndPoints.ADD_NEW_PET);
        Integer id = (Integer)responseUtil.getJsonPathValue(response, "id");
        responseUtil.printResponse(response);
        response = requestUtil.getSingleResponse(String.valueOf(id), IEndPoints.FIND_PET_BY_ID);
        response.prettyPrint();
    }


}
