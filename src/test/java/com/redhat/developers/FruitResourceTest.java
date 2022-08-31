package com.redhat.developers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.allOf;;



@QuarkusTest
public class FruitResourceTest {
    @Test
    public void testFruitEndpoint() {
        given()
          .when().get("/fruit")
          .then()
             .statusCode(200)
             .body(containsString("[{"));
    }
    
    @Test
    public void testSpringFruitEndpoint() {
        given()
            .when().get("/spring-fruit")
            .then()
                .statusCode(200)
                .body(containsString("[{"));
    }


    
    @Test
    public void testAppleEndpoint(){
        given()
            .when().get("/fruit/Apple")
            .then()
                .statusCode(200)
                .body(is("[{\"calories\":52.0,\"carbohydrates\":11.4,\"name\":\"Apple\",\"season\":\"Fall\",\"sugar\":10.3}]"));
    }


     
    @Test
    public void testSpringFruitAppleEndpoint(){
        given()
            .when().get("/spring-fruit/Apple")
            .then()
                .statusCode(200)
                .body(is("[{\"id\":9,\"name\":\"Apple\",\"season\":\"Fall\"}]"));
    }




    @Test
    public void testSeasonEndpoint(){
        given()
            .when().get("/fruit?season=Spring") //Es muss jetzt halt immer eine Frucht mit der Saison "Spring" haben, sonst schlägt Test fehl. 
            .then()
                .statusCode(200)
                .body(allOf(
                    not(containsString("\"season\":\"Summer\"")), 
                    not(containsString("\"season\":\"Winter\"")), 
                    not(containsString("\"season\":\"Fall\"")),
                    containsString("\"season\":\"Spring\""))); 
    }

    @Test
    public void testSpringFruitSeasonEndpoint(){
        given()
            .when().get("/spring-fruit?season=Spring") //Es muss jetzt halt immer eine Frucht mit der Saison "Spring" haben, sonst schlägt Test fehl. 
            .then()
                .statusCode(200)
                .body(allOf(
                    not(containsString("\"season\":\"Summer\"")), 
                    not(containsString("\"season\":\"Winter\"")), 
                    not(containsString("\"season\":\"Fall\"")),
                    containsString("\"season\":\"Spring\""))); 
    }



    @Test
    public void testNameEndpoint(){
        given()
            .when().get("/fruit?name=Banana") //Es muss jetzt immer eine Banane in der Datenbank haben, sonst schlägt Test fehl. 
            .then()
                .statusCode(200)
                .body(is("[{\"calories\":96.0,\"carbohydrates\":22.0,\"name\":\"Banana\",\"season\":\"Summer\",\"sugar\":17.2}]"));
    }

    @Test
    public void testSpringFruitNameEndpoint(){
        given()
            .when().get("/spring-fruit?name=Banana") //Es muss jetzt immer eine Banane in der Datenbank haben, sonst schlägt Test fehl. 
            .then()
                .statusCode(200)
                .body(is("[{\"id\":6,\"name\":\"Banana\",\"season\":\"Summer\"}]"));
    }
}
