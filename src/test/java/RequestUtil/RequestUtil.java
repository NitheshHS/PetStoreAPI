package RequestUtil;

import base.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RequestUtil {

    private ExtentReportManager manager;
    public RequestUtil(String URI){
       RestAssured.baseURI=URI;
       manager=ExtentReportManager.getInstance();
    }
    public Response getResponse(RequestSpecification spec, String endpoint){
        manager.info("Specs: "+spec.log().all().toString());
        manager.info("EndPoint: "+endpoint);
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .get(endpoint)
                .then().extract().response();
        return response;
    }

    public Response getSingleResponse(String id, String endpoint){
        manager.info("id: "+id);
        manager.info(String.format("endpoint: {%s}", endpoint));
       return RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(endpoint)
                .then().extract().response();
    }

    public Response postResponse(Object payload, String endpoint){
        manager.info("Payload: "+payload.toString());
        manager.info("Endpoint: "+endpoint);
       return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint)
                .then().extract().response();
    }
}
