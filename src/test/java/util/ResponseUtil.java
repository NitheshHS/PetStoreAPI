package util;

import base.ExtentReportManager;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.ContentType;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {

   private Response response;

    private ExtentReportManager manager;
    public ResponseUtil(){
        manager = ExtentReportManager.getInstance();
    }

    public Object getJsonPathValue(Response response,String jsonPath){
        manager.info("Json path: "+jsonPath);
        Object obj = response.getBody().jsonPath().get(jsonPath);
        manager.info("Json value: "+obj.toString());
        return obj;
    }

    public void printResponse(Response response){
        this.response = response;
        List<String> headers = response.getHeaders().asList().stream()
                .map(header -> header.getName() + "==" + header.getValue()).collect(Collectors.toList());
        manager.info("headers: "+headers.toString());
        manager.info("response Body: "+response.getBody().print());
        response.then().log().all();
    }

    public void verifyStatusCode(int statusCode){
        manager.info("Verifying the status code");
        response.then().assertThat().statusCode(statusCode);
    }

    public void verifyHeader(String headerKey, String headerValue){
        manager.info("Verifing header: "+headerValue+" : "+headerValue);
        response.then()
                .assertThat().header(headerKey, headerValue);
    }

    public void verifyContentTypeIsJson(){
        response.then().assertThat().contentType(io.restassured.http.ContentType.JSON);
    }

}
