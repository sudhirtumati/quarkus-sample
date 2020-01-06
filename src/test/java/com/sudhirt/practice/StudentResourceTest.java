package com.sudhirt.practice;

import static io.restassured.RestAssured.given;

import javax.transaction.Transactional;

import com.google.gson.JsonObject;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class StudentResourceTest {

    @Test
    public void get_should_return_empty_list_when_students_are_not_available() {
        given().when().get("/students").then().statusCode(200).body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void put_should_throw_404_error_when_student_is_not_available() {
        JsonObject payload = new JsonObject();
        payload.addProperty("firstName", "First Name");
        payload.addProperty("lastName", "Last Name");
        payload.addProperty("gender", "MALE");
        payload.addProperty("dateOfBirth", "01-01-2000");
        payload.addProperty("id", "STU1");
        given().contentType("application/json").body(payload.toString()).when().put("/students/STU1").then()
                .statusCode(404);
    }

    @Test
    public void delete_should_throw_404_error_when_student_is_not_available() {
        given().when().delete("/students/STU1").then().statusCode(404);
    }

    @Test
    @Transactional
    public void duplicate_post_should_throw_409_error_when_student_is_not_available() throws Exception {
        JsonObject payload = new JsonObject();
        payload.addProperty("firstName", "First Name");
        payload.addProperty("lastName", "Last Name");
        payload.addProperty("gender", "MALE");
        payload.addProperty("dateOfBirth", "01-01-2000");
        payload.addProperty("id", "STU1");
        given().contentType("application/json").body(payload.toString()).when().post("/students").then()
                .statusCode(201);
        given().contentType("application/json").body(payload.toString()).when().post("/students").then()
                .statusCode(409);
    }

}