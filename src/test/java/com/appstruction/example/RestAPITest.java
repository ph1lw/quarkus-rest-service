/*******************************************************************************
 * Author: (2019) Philipp Wurm <phiwu@gmx.at>
 *
 * This file is part of quarkus-rest-service
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package com.appstruction.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class RestAPITest {

    private static final int RAND_MIN = 0;
    private static final int RAND_MAX = 100;

    @Test
    public void isRESTHelloAvailable() {
        given()
                .when()
                    .get("/rest/v1/hello")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body(is("Hello World"));
    }

    @Test
    public void isRESTHelloUserAvailable() {
        final String testUserName = "TestUser";

        given()
                    .pathParam("name", testUserName)
                .when()
                    .get("/rest/v1/hello/{name}")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body(is(String.format("Hello %s", testUserName)));
    }

    @Test
    public void isRESTRandomAvailableAndValide() {
        final int randFrom = ThreadLocalRandom.current().nextInt(RAND_MIN, RAND_MAX / 2);
        final int randTo = ThreadLocalRandom.current().nextInt((RAND_MAX / 2) + 1, RAND_MAX);
        final String payLoad = String.format("{\"range_from\": %d, \"range_to\": %d}", randFrom, randTo);

        String responsePayload = given()
                .when()
                    .contentType(ContentType.JSON)
                    .body(payLoad)
                    .post("/rest/v1/random")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                        .body()
                            .asString();

        assertFalse(responsePayload.isBlank());

        assertTrue(responsePayload
                .substring(1, responsePayload.length() - 1)
                .startsWith("New random value"));

        assertTrue(responsePayload.contains(":"));

        /*
            1. clean payload - remove leading and trailing double quote
            2. find last element after ":" - must be a number string
            3. cast to integer
            4. filter if number is expected between random ranges
         */
        assertTrue(Stream
                .of(responsePayload
                        .substring(1, responsePayload.length() - 1)
                        .split(":"))
                .reduce((first, second) -> second)
                .map(r -> Integer.parseInt(r.trim()))
                .filter(r -> r >= randFrom && r <= randTo)
                .isPresent());
    }
}
