package test;

import File.JsonFileUtil;
import base.BaseTest;
import base.IEndPoints;
import File.JsonFilePath;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import requestpojo.Category;
import requestpojo.CreatePet;
import requestpojo.Tag;

public class CreateNewPetTest extends BaseTest {

    @Test(groups = {"smokeTest"})
    public void createPet() throws Exception {
        String json=JsonFileUtil.loadJsonFile(JsonFilePath.POST_TESTDATA);
        int id = (int)JsonFileUtil.read(json, "new_pet.id");

        Category category=new Category();
        category.setId(id);
        category.setName(String.valueOf(JsonFileUtil.read(json, "new_pet.category.name")));

        Tag tag1=new Tag();
        tag1.setId(id);
        tag1.setName(String.valueOf(JsonFileUtil.read(json, "new_pet.tag[0].name")));

        String[] photoUrl= {String.valueOf(JsonFileUtil.read(json, "new_pet.photoUrls[0]"))};

        CreatePet pet = new CreatePet();
        pet.setId(id);
        pet.setCategory(category);
        pet.setName(String.valueOf(JsonFileUtil.read(json, "new_pet.name")));
        pet.setTags(new Tag[]{tag1});
        pet.setPhotoUrls(photoUrl);
        pet.setStatus(String.valueOf(JsonFileUtil.read(json, "new_pet.status")));

        Response response = requestUtil.postResponse(pet, IEndPoints.ADD_NEW_PET);
        responseUtil.printResponse(response);
        responseUtil.verifyStatusCode(200);
        responseUtil.verifyContentTypeIsJson();

    }

}
