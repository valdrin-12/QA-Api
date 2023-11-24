//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import Pojo.Api;
import Pojo.GetCourse;
import Pojo.WebAutomation;

public class oAuthTest {
    public oAuthTest() {
    }

    public static void main(String[] args) throws InterruptedException {
        String[] courseTitles = new String[]{"Selenium Webdriver Java", "Cypress", "Protractor"};
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

       // driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(new CharSequence[]{"fdfd"});
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(new CharSequence[]{Keys.ENTER});
        Thread.sleep(3000L);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(new CharSequence[]{"fxfe"});
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(new CharSequence[]{Keys.ENTER});
        Thread.sleep(4000L);
        String url = driver.getCurrentUrl();
        String partialcode = url.split("code=")[1];
        String code = partialcode.split("&scope")[0];
        System.out.println(code);
        String accessTokenResponse = ((Response)((RequestSpecification)RestAssured.given().urlEncodingEnabled(false).queryParams("code", code, new Object[0]).queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com", new Object[0]).queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W", new Object[0]).queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php", new Object[0]).queryParams("grant_type", "authorization_code", new Object[0]).when().log().all()).post("https://www.googleapis.com/oauth2/v4/token", new Object[0])).asString();
        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");
        GetCourse gc = (GetCourse)((Response)RestAssured.given().queryParam("access_token", new Object[]{accessToken}).expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php", new Object[0])).as(GetCourse.class);
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        System.out.println(((Api)gc.getCourses().getApi().get(1)).getCourseTitle());
        List<Api> apiCourses = gc.getCourses().getApi();

        for(int i = 0; i < apiCourses.size(); ++i) {
            if (((Api)apiCourses.get(i)).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                System.out.println(((Api)apiCourses.get(i)).getPrice());
            }
        }

        ArrayList<String> a = new ArrayList();
        List<WebAutomation> w = gc.getCourses().getWebAutomation();

        for(int j = 0; j < w.size(); ++j) {
            a.add(((WebAutomation)w.get(j)).getCourseTitle());
        }

        List<String> expectedList = Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expectedList));
    }
}
