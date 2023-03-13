package controllers;

import org.junit.Test;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.GET;
import static play.test.Helpers.POST;

public class RecipeControllerRoutesTest extends WithApplication {

    //Tester funcional de rutas del controlador de recetas
    //Base de datos imaginaria
    @Override
    protected Application provideApplication(){
        return Helpers.fakeApplication(Helpers.inMemoryDatabase());
    }


    //1-Testeo de ruta, creacion de recetas:
    @Test
    public void testPostRecipe() {

        String jsonInputString = "{\n" +
                "                            \"name\":\"fabada\",\n" +
                "                            \"time\":35,\n" +
                "                            \"title\":\"Fabada_Asturiana\",\n" +
                "                            \"typeFood\":\"Carnivoro\"\n" +
                "                        }";

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/recipes")
                .header("Accept-Language", "en, es, es-ES")
                .header("Content-Type", "application/json")
                .bodyJson(Json.parse(jsonInputString));

        Result result = Helpers.route(provideApplication(), request);
        assertEquals(201, result.status());
    }

    //Testeo de ruta, obtención de recetas:
    @Test
    public void testRetrieveRecipe() {

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/recipes")
                .header("Accept-Language", "en, es, es-ES")
                .header("Content-Type", "application/json");


        Result result = Helpers.route(provideApplication(), request);
        assertEquals(200, result.status());
    }
}
