package test;

import static File.JsonFileUtil.*;
import base.BaseTest;
import base.IEndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import requestpojo.Category;
import requestpojo.CreatePet;
import requestpojo.Tag;

public class DeletePetTest extends BaseTest {

    @Test
    public void deletePet() throws Exception {
        String json = loadJsonFile("./src/test/resources/PostTestData.json");
        Category  category = new Category((Integer) read(json,"$.new_pet.category.id"),
                String.valueOf(read(json, "$.new_pet.category.name")));
        Tag[] tag={new Tag((Integer) read(json,"$.new_pet.tag[0].id"),
                String.valueOf(read(json, "$.new_pet.tag[0].name")))
        };
        String[] photourls = {String.valueOf(read(json,"$.new_pet.photoUrls[0]"))};
        CreatePet createPetPayload = new CreatePet((Integer) read(json,"$.new_pet.id"),
                category,
                String.valueOf(read(json, "$.new_pet.name")),
                photourls,
                tag,
                String.valueOf(read(json, "$.new_pet.status")));
        Response response = requestUtil.postResponse(createPetPayload, IEndPoints.ADD_NEW_PET);
        Integer id = (Integer) responseUtil.getJsonPathValue(response, "id");
        responseUtil.printResponse(response);
        responseUtil.verifyStatusCode(200);
        responseUtil.verifyContentTypeIsJson();

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addPathParam("petId", id);

        response=requestUtil.deleteRequest(requestSpecBuilder.build(), IEndPoints.DELETE_PET);
        responseUtil.printResponse(response);

    }
}
