package com.appstruction.example.rest;

import com.appstruction.example.random.RandomRange;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class QuarkusRestService implements QuarkusRestServiceAPI {
    @Override
    public Response hello() {
        return Response.ok("Hello World").build();
    }

    @Override
    public Response helloUser(String name) {
        String userName = Objects.requireNonNull(name);

        if(userName.isBlank()) {
            userName = "Stranger";
        }

        return Response.ok(String.format("Hello %s", userName)).build();
    }

    @Override
    public Response randomNumber(String rangeAsJson) {
        final Jsonb jsonb = JsonbBuilder.create();
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
