package test;

import static io.restassured.RestAssured.given;

import com.casumo.api.utils.ApiHelper;
import com.casumo.api.utils.Constants;
import com.casumo.api.utils.ExcelUtil;
import com.casumo.api.utils.ResponseConstants;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VowelTest extends TestBase {

    @Test(dataProvider = "testData")
    public void vowelTest(String input) {

        extentLogger = report.createTest("Vowel Test");
        RestAssured.baseURI = props.getProperty("api.url");

        extentLogger.info("Get input from xlsx file");
        extentLogger.info("Input is : " + input);
        String actualResponse =
                given()
                        .when()
                        .get(props.getProperty("api.path") + input)
                        .then()
                        .assertThat()
                        .statusCode(ResponseConstants.STATUS_CODE_OK)
                        .and()
                        .extract()
                        .response()
                        .asString();

        extentLogger.info("Actual response   is : " + actualResponse);
        System.out.println("Response is :" + actualResponse);

        String expectedResponse = ":" + ApiHelper.extractVowelsRecursive(input).trim();
        actualResponse = actualResponse.trim();
        extentLogger.info("Expected response is : " + actualResponse);

        Assert.assertEquals(actualResponse, expectedResponse, "Expected response is not correct.");
        extentLogger.pass(Constants.PASSED);
    }

    @DataProvider
    public Object[][] testData() {
        ExcelUtil qa3short = new ExcelUtil(Constants.TEST_DATA_XSLX_PATH, Constants.SHEET_NAME);
        String[][] dataArray = qa3short.getDataArrayWithoutFirstRow();
        return dataArray;
    }
}
