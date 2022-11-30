package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

@QuarkusTest
public class EntryControllerTest {

    @Test
    public void testDeleteEndpoint() {
        Long id = given()
                .header("Content-type", "application/json")
                .body(new Entry(LocalDateTime.now(), LocalDateTime.now()))
                .when().post("/entries").getBody().as(Entry.class).getId();
        given()
                .when().delete("/entries/" + id)
                .then()
                .statusCode(204)
                .body(is(""));
        given()
                .when().get("/entries/" + id)
                .then()
                .statusCode(204)
                .body(is(""));
    }

    @Test
    public void testUpdateEndpoint() {
        Long id = given()
                .header("Content-type", "application/json")
                .body(new Entry(LocalDateTime.now(), LocalDateTime.now()))
                .when().post("/entries").getBody().as(Entry.class).getId();
        LocalDateTime expLocalDateTime = LocalDateTime.now();
        given()
                .header("Content-type", "application/json")
                .body(new Entry(id, expLocalDateTime, LocalDateTime.now()))
                .when().put("/entries/")
                .then()
                .statusCode(200);
        LocalDateTime actualLocalDateTime = given()
                .when().get("/entries/" + id).getBody().as(Entry.class).getCheckIn();
        assertEquals(expLocalDateTime.getSecond(), actualLocalDateTime.getSecond());
    }

}