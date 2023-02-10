package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.RecipeModel;
import play.cache.Cached;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import views.RecipeInputResource;
import views.RecipeResource;
import views.xml.recipe;
import play.cache.SyncCacheApi;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecipeController extends Controller{
    @Inject
    private FormFactory formFactory;

    @Inject
    private MessagesApi messagesApi;

    @Inject
    private SyncCacheApi cache;

    public Result create(Http.Request req) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        //Obtener los datos de la petición de creado mediante formulario
        Form<RecipeInputResource> recipeForm = formFactory.form(RecipeInputResource.class).bindFromRequest(req);
        RecipeResource recipeResource;

        //Gestión de errores en el formulario
        if(recipeForm.hasErrors()){
            return Results.badRequest(recipeForm.errorsAsJson());

        }else{
            recipeResource = recipeForm.get();
            String msg = messages.at("create");
            System.out.println(msg+" "+ recipeResource.getName());

        }

        //Creación de la receta y conversión a modelo
        RecipeModel recipeModel = recipeResource.toModel();
        recipeModel.save();

        //Conversión a JSON
        ObjectNode jsonResult = Json.newObject();
        jsonResult.put("id", recipeModel.getId());

        return Results.created(jsonResult);

    }

    public Result delete(Http.Request req, Integer id) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        //Obtener los datos de la petición de eliminado mediante formulario
        Form<RecipeInputResource> deleteRecipeForm = formFactory.form(RecipeInputResource.class).bindFromRequest(req);
        RecipeInputResource recipeResource;

        //Búsqueda por id de la receta a eliminar
        RecipeModel um = RecipeModel.findById(Long.valueOf(id));

        if (um == null) {
            return Results.notFound();

        }else{
            //Gestión de errores
            if(deleteRecipeForm.hasErrors()){
                return Results.badRequest(deleteRecipeForm.errorsAsJson());

            }else{
                recipeResource = deleteRecipeForm.get();
                String msg = messages.at("delete");
                um.delete(id);

                return Results.ok(msg+" "+ recipeResource.getName());

            }

        }

    }

    public Result update(Http.Request req, Integer id) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        //Obtener los datos nuevos de la petición de modificación mediante formulario
        Form<RecipeInputResource> newRecipeForm = formFactory.form(RecipeInputResource.class).bindFromRequest(req);
        RecipeInputResource recipeResource;

        //Búsqueda de la receta a modificar por id
        RecipeModel um = RecipeModel.findById(Long.valueOf(id));

        if (um == null) {
            return Results.notFound();
        }else{
            //Gestión de errores
            if(newRecipeForm.hasErrors()){
                return Results.badRequest(newRecipeForm.errorsAsJson());

            }else{
                recipeResource = newRecipeForm.get();
                String msg = messages.at("update");
                um.setName(recipeResource.getName());
                um.setAge(recipeResource.getAge());

                //Actualizacion de datos en el modelo
                um.save();

                //Conversión a JSON
                ObjectNode jsonResult = Json.newObject();
                jsonResult.put("id", um.getId());

                return Results.ok(msg+" "+ recipeResource.getName());

            }

        }

    }

    @Cached(key="recipe-view", duration = 10)
    public Result retrieve(Http.Request req, Integer id) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        RecipeModel rm;
        Optional<Object> optRecipe = cache.get("recipe");

        if (optRecipe.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            rm = (RecipeModel) optRecipe.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            rm = RecipeModel.findById(Long.valueOf(id));
            cache.set("recipe", rm);
        }

        if (rm == null) {
            return Results.notFound();
        }

        RecipeResource recipeResource = new RecipeResource(rm);
        Result res;

        //Vista Json/Xml
        if(req.accepts("application/json")){
            res = Results.ok(recipeResource.toJson());

        }else if (req.accepts("application/xml")){
            //Con templates
            res = Results.ok(recipe.render(recipeResource.getName(), recipeResource.getAge()));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }

    @Cached(key="all-recipes-view", duration = 10)
    public Result retrieveAll(Http.Request req) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        List<RecipeModel> recipes;
        Optional<Object> optRecipes = cache.get("all.recipes");

        if (optRecipes.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            recipes = (List<RecipeModel>) optRecipes.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            recipes = RecipeModel.findAll();
            cache.set("all-recipes", recipes);
        }

        //Enfoque funcional:
        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

        //Vista Json/Xml
        /*if(req.accepts("application/json")){
            res = Results.ok(json);

        }else if (req.accepts("application/xml")){
            //Con templates
            res = Results.ok(recipe.render(recipeResource.getName(), recipeResource.getAge()));

        }else {
            res = Results.unsupportedMediaType();
        }*/

        return res;

    }
}
