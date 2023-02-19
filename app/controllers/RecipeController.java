package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.RecipeModel;
import models.RecipeTitle;
import models.RecipeValoration;
import models.RecipeIngredient;
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
import views.xml.allrecipes;
import play.cache.SyncCacheApi;
import javax.inject.Inject;
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

        //Búsqueda por id de la receta a eliminar
        RecipeModel rm = RecipeModel.findById(Long.valueOf(id));

        if (rm == null) {
            return Results.notFound();

        }else{

                String msg = messages.at("delete");
                rm.delete(id);

                return Results.ok(msg+" "+ id);


        }

    }

    public Result update(Http.Request req, Integer id) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        //Obtener los datos nuevos de la petición de modificación mediante formulario
        Form<RecipeInputResource> newRecipeForm = formFactory.form(RecipeInputResource.class).bindFromRequest(req);
        RecipeInputResource recipeResource;

        //Búsqueda de la receta a valorar por id
        RecipeModel um = RecipeModel.findById(Long.valueOf(id));

        if (um == null) {
            return Results.notFound();
        }else{
            //Gestión de errores
            if(newRecipeForm.hasErrors()){
                return Results.badRequest(newRecipeForm.errorsAsJson());

            }else{
                recipeResource = newRecipeForm.get();
                RecipeTitle rt = recipeResource.toModel().getTitle();
                rt.setTitle(rt.getTitle().toLowerCase());

                String msg = messages.at("update");
                um.setName(recipeResource.getName().toLowerCase());
                um.setTime(recipeResource.getTime());
                um.setTypeFood(recipeResource.getTypeFood().toLowerCase());
                um.setTitle(rt);

                //Actualizacion de datos en el modelo
                um.save();
                um.refresh();

                //Conversión a JSON
                ObjectNode jsonResult = Json.newObject();
                jsonResult.put("id", um.getId());

                return Results.ok(msg+" "+ recipeResource.getName());

            }

        }

    }

    public Result valorateRecipe(Http.Request req, Integer id, Integer point) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        //Búsqueda de la receta a valorar por id
        RecipeModel um = RecipeModel.findById(Long.valueOf(id));

        if (um == null) {
            return Results.notFound();
        }else{
              RecipeValoration rv = new RecipeValoration();
                rv.setPuntuation(point);

                String msg = messages.at("puntuate");
                um.addValoration(rv);

                //Actualizacion de datos en el modelo
                um.save();
                um.refresh();

                //Conversión a JSON
                ObjectNode jsonResult = Json.newObject();
                jsonResult.put("id", um.getId());

                return Results.ok(msg+" "+ id);

            }


    }


    public Result inputIngredientsRecipe(Http.Request req, Integer id, String ingredient) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        //Obtener los datos nuevos de la petición de modificación mediante formulario
        Form<RecipeIngredient> newIngredientForm = formFactory.form(RecipeIngredient.class).bindFromRequest(req);
        RecipeIngredient recipeIngredient;

        //Búsqueda de la receta a valorar por id
        RecipeModel um = RecipeModel.findById(Long.valueOf(id));

        if (um == null) {
            return Results.notFound();
        }else{
            //Gestión de errores
            if(newIngredientForm.hasErrors()){
                return Results.badRequest(newIngredientForm.errorsAsJson());

            }else{

                recipeIngredient = newIngredientForm.get();
                um.getIngredients().add(recipeIngredient);
                um.save();
                um.refresh();


                String msg = messages.at("ingredient");
                um.addIngredient(recipeIngredient);

                //Conversión a JSON
                ObjectNode jsonResult = Json.newObject();
                jsonResult.put("id", um.getId());

                return Results.ok(msg+" "+ recipeIngredient.getNombreIngrediente());

            }

        }

    }



    @Cached(key="recipe-view", duration = 5)
    public Result retrieveById(Http.Request req, Integer id) {

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

            res = Results.ok(recipe.render(recipeResource.getName(), recipeResource.getTime(), recipeResource.getTitle(), recipeResource.getTypeFood()));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }

    @Cached(key="all-recipes-view", duration = 5)
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
        if(req.accepts("application/json")){
            res = Results.ok(json);

        }else if (req.accepts("application/xml")){

            res = Results.ok(allrecipes.render(recipes));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }

    @Cached(key="all-recipes-by-type-view", duration = 5)
    public Result retrieveAllByType(Http.Request req, String typeFood) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        List<RecipeModel> recipes;
        Optional<Object> optRecipes = cache.get("all.recipes.by.type");

        if (optRecipes.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            recipes = (List<RecipeModel>) optRecipes.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            recipes = RecipeModel.findByType(typeFood);
            cache.set("all-recipes-by-type", recipes);
        }

        //Enfoque funcional:
        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res;

        //Vista Json/Xml
        if(req.accepts("application/json")){
            res = Results.ok(json);

        }else if (req.accepts("application/xml")){

            res = Results.ok(allrecipes.render(recipes));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }

    @Cached(key="recipe-by-title-view", duration = 5)
    public Result retrieveByTitle(Http.Request req, String title) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        RecipeModel rm;
        Optional<Object> optRecipe = cache.get("recipe.by.title");

        if (optRecipe.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            rm = (RecipeModel) optRecipe.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            rm = RecipeModel.findByTitle(title);
            cache.set("recipe.by.title", rm);
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

            res = Results.ok(recipe.render(recipeResource.getName(), recipeResource.getTime(), recipeResource.getTitle(), recipeResource.getTypeFood()));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }

    @Cached(key="all-recipes-by-time-view", duration = 5)
    public Result retrieveAllByTime(Http.Request req, Integer time) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        List<RecipeModel> recipes;
        Optional<Object> optRecipes = cache.get("all.recipes.by.time");

        if (optRecipes.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            recipes = (List<RecipeModel>) optRecipes.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            recipes = (List<RecipeModel>) RecipeModel.findByTime(time);
            cache.set("all-recipes-by-time", recipes);
        }

        //Enfoque funcional:
        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

        //Vista Json/Xml
        if(req.accepts("application/json")){
            res = Results.ok(json);

        }else if (req.accepts("application/xml")){

            res = Results.ok(allrecipes.render(recipes));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }


    @Cached(key="all-recipes-by-ingredient-view", duration = 5)
    public Result retrieveAllByIngredient(Http.Request req, String ingredient) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        List<RecipeModel> recipes;
        Optional<Object> optRecipes = cache.get("all.recipes.by.ingredient");

        if (optRecipes.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            recipes = (List<RecipeModel>) optRecipes.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            recipes = (List<RecipeModel>) RecipeModel.findByIngredient(ingredient);
            cache.set("all-recipes-by-ingredient", recipes);
        }

        //Enfoque funcional:
        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

        //Vista Json/Xml
        if(req.accepts("application/json")){
            res = Results.ok(json);

        }else if (req.accepts("application/xml")){

            res = Results.ok(allrecipes.render(recipes));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }


    @Cached(key="all-recipes-by-name-view", duration = 5)
    public Result retrieveAllByName(Http.Request req, String name) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        List<RecipeModel> recipes;
        Optional<Object> optRecipes = cache.get("all.recipes.by.name");

        if (optRecipes.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            recipes = (List<RecipeModel>) optRecipes.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            recipes = (List<RecipeModel>) RecipeModel.findByName(name);
            cache.set("all-recipes-by-name", recipes);
        }

        //Enfoque funcional:
        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

        //Vista Json/Xml
        if(req.accepts("application/json")){
            res = Results.ok(json);

        }else if (req.accepts("application/xml")){

            res = Results.ok(allrecipes.render(recipes));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }

    @Cached(key="all-recipes-by-valoration-view", duration = 5)
    public Result retrieveAllByValoration(Http.Request req, Integer point) {

        //Internacionalización de la API mediante idiomas
        Messages messages = messagesApi.preferred(req);

        List<RecipeModel> recipes;
        Optional<Object> optRecipes = cache.get("all.recipes.by.valoration");

        if (optRecipes.isPresent()) {
            String msg1 = messages.at("cached");
            System.out.println(msg1);
            recipes = (List<RecipeModel>) optRecipes.get();

        } else {
            String msg2 = messages.at("notCached");
            System.out.println(msg2);
            recipes = (List<RecipeModel>) RecipeModel.findByValoration(point);
            cache.set("all-recipes-by-valoration", recipes);
        }

        //Enfoque funcional:
        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

        //Vista Json/Xml
        if(req.accepts("application/json")){
            res = Results.ok(json);

        }else if (req.accepts("application/xml")){

            res = Results.ok(allrecipes.render(recipes));

        }else {
            res = Results.unsupportedMediaType();
        }

        return res;

    }
}
