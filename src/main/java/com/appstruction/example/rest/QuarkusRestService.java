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

package com.appstruction.example.rest;

import com.appstruction.example.random.RandomRange;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.core.Response;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class QuarkusRestService implements QuarkusRestServiceAPI {

    private final Jsonb jsonb;

    public QuarkusRestService() {
        jsonb = JsonbBuilder.create(new JsonbConfig());
    }

    @Override
    public Response hello() {
        return Response.ok("Hello World").build();
    }

    @Override
    public Response helloUser(String name) {
        return Response.ok(String.format("Hello %s", name)).build();
    }

    @Override
    public Response randomNumber(String rangeAsJson) {
        final RandomRange randomRange = jsonb.fromJson(rangeAsJson, RandomRange.class);

        final String jsonString = jsonb.toJson(String.format("New random value [%d-%d]: %d",
                randomRange.getRangeFrom(),
                randomRange.getRangeTo(),
                ThreadLocalRandom.current().nextInt(
                        randomRange.getRangeFrom(),
                        randomRange.getRangeTo()+1)),
                String.class);

        return Response.ok(jsonString).build();
    }
}
