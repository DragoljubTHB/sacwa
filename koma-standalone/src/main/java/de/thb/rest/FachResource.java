package de.thb.rest;

import de.thb.data.Fach;
import de.thb.repository.api.FachRepository;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("fachs")
public class FachResource {
    @Inject
    FachRepository repo;

    @GET
    public JsonArray getFachs(){
        return repo.getAll().stream()
                .map(c ->
                        Json.createObjectBuilder()
                        .add("name", c.getName())
                        .add("kommentar", c.getKommentar())
                        .build())
                .collect(Json::createArrayBuilder
                        , JsonArrayBuilder::add
                        , JsonArrayBuilder::add)
                .build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createFach(@NotNull @Valid final Fach fach){
        repo.create(fach);
        System.out.println("Fach: "+fach);
    }


}
