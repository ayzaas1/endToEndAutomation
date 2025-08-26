package api.stepDefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class InvoiceStepsTests {

    RequestSpecification request;
    Response response;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3NTg4MTI0OTEsImlhdCI6MTc1NjIyMDQ5MSwidXNlcm5hbWUiOiJtaWNoZWFsLnBvd2xvd3NraUB5YWhvby5jb20ifQ.laMvWCCL7yejJg4ERIcTwJ0RSUPfxqcmJLFdF3jUeEtHWV6Esp6Oe7ip6NPA-0HqUwnFIRdIY-wD_9C1DTLwbw";
    JSONObject requestBody = new JSONObject();


    @Test
    public void method1() {
        System.out.println("THIS IS METHOD FROM INVOICE TESTS CLASS");
    }


}
