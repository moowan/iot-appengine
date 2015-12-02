package com.pirate.iot.appengine.ressources;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.pirate.iot.appengine.entities.Animal;
import com.pirate.iot.appengine.entities.Pi;
import com.pirate.iot.appengine.virtual.CreateRessourcesVirtual;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/animals/")
public class AnimalsRessource {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    @GET
    @Produces("application/json")
    public HashMap<String, List> fetchAll() {
        List subList = new ArrayList();
        HashMap<String, List> result = new HashMap<String,List>();

        result.put("animals",this.getFake());

        return result;
    }

    @GET
    @Consumes("application/json")
    @Path("/fake")
    public Response fakeAnimals()
    {
        ArrayList<Animal> animalsList = this.getFake();

        for (Animal animal:animalsList) {
            animal.register(datastore);
        }

        return Response.status(201).entity("FAKE Animals").build();
    }

    private ArrayList<Animal> getFake()
    {
        ArrayList<Animal> animalsList = new ArrayList<Animal>();

        String json = "[\n" +
                "  {\n" +
                "    \"race\": \"0\",\n" +
                "    \"uUID\": \"51fa8287-a719-4c1b-8177-7ec829f6e08e\",\n" +
                "    \"name\": \"Amet\",\n" +
                "    \"age\": \"2014-05-26T01:17:18 -02:00\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"race\": \"1\",\n" +
                "    \"uUID\": \"51fa8287-a719-4c1b-8177-7ec829f6e08e\",\n" +
                "    \"name\": \"Carraccio\",\n" +
                "    \"age\": \"2013-05-26T01:17:18 -02:00\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"race\": \"0\",\n" +
                "    \"uUID\": \"51fa8287-a719-4c1b-8177-7ec829f6e08e\",\n" +
                "    \"name\": \"Eria\",\n" +
                "    \"age\": \"2014-05-26T01:17:18 -02:00\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"race\": \"1\",\n" +
                "    \"uUID\": \"51fa8287-a719-4c1b-8177-7ec829f6e08e\",\n" +
                "    \"name\": \"Pocket\",\n" +
                "    \"age\": \"2012-05-26T01:17:18 -02:00\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"race\": \"0\",\n" +
                "    \"uUID\": \"51fa8287-a719-4c1b-8177-7ec829f6e08e\",\n" +
                "    \"name\": \"Regliss\",\n" +
                "    \"age\": \"2014-01-26T01:17:18 -02:00\"\n" +
                "  }\n" +
                "]";

        JSONArray piListJson = new JSONArray(json);

        for (int i=0;i< piListJson.length(); i++)
        {
            Animal animal = new Animal(piListJson.getJSONObject(i));

            animalsList.add(animal);
        }

        return animalsList;
    }
}