import com.sun.tools.jxc.ap.Const;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.stream.Location;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static java.awt.geom.Path2D.contains;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindRandomUser {


    int id = (int) Math.floor((Math.random() * 100) + 1);
    String userUrl = "https://jsonplaceholder.typicode.com/users/" + id;
    String postUrl = "https://jsonplaceholder.typicode.com/posts/" + id;

    @Test
    public void findUSerEmail() {

        System.out.println(userUrl);
        String email = given().when().get(userUrl).then().extract().path("email");
        System.out.println(email);
    }

    @Test
    public void get_this_users_associated_posts() {
        given().when().get(postUrl).then().assertThat().log().body();
        Assert.assertTrue(1 <= id && id <= 100);
    }

    @Test
    public void post_using_same_userID() {

        String post = "{\n" + "  \"title\":\"Done by Asmaa\",\n" + "  \"body\":\"every QA Should study RestAssured ^_^\",\n" + "}";

        given().contentType(ContentType.JSON).body(post).log().all().when().post(postUrl).then().assertThat().statusCode(500);
    }
}
