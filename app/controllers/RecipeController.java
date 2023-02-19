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
        String msg = messages.at("create");

        //Obtener los datos del body de la petición de creado mediante formulario
        Form<RecipeInputResource> recipeForm = formFactory.form(RecipeInputResource.class).bindFromRequest(req);
        RecipeResource recipeResource;

        //Gestión de errores en el formulario
        if(recipeForm.hasErrors()){
            return Results.badRequest(recipeForm.errorsAsJson());

        }else{
            recipeResource = recipeForm.get();

        }

        //Creación de la receta y conversión a modelo
        RecipeModel recipeModel = recipeResource.toModel();
        recipeModel.save();

        //Conversión a JSON
        ObjectNode jsonResult = Json.newObject();
        jsonResult.put("id", recipeModel.getId());

        return Results.created(msg+" "+recipeModel.getName()+jsonResult);

    }

    public Result delete(Http.Request req, Integer id) {

        Messages messages = messagesApi.preferred(req);

        //Búsqueda por id de la receta a eliminar
        RecipeModel rm = RecipeModel.findById(Long.valueOf(id));

        //Gestión de errores
        if (rm == null) {
            return Results.notFound();

        }else{

            String msg = messages.at("delete");
            rm.delete(id);

            return Results.ok(msg+" "+ id);

        }

    }

    public Result update(Http.Request req, Integer id) {

        Messages messages = messagesApi.preferred(req);

        Form<RecipeInputResource> newRecipeForm = formFactory.form(RecipeInputResource.class).bindFromRequest(req);
        RecipeInputResource recipeResource;

        RecipeModel rm = RecipeModel.findById(Long.valueOf(id));

        if (rm == null) {
            return Results.notFound();
        }else{
            if(newRecipeForm.hasErrors()){
                return Results.badRequest(newRecipeForm.errorsAsJson());

            }else{
                recipeResource = newRecipeForm.get();
                RecipeTitle rt = recipeResource.toModel().getTitle();
                rt.setTitle(rt.getTitle().toLowerCase().replaceAll(" ","_"));

                String msg = messages.at("update");
                rm.setName(recipeResource.getName().toLowerCase());
                rm.setTime(recipeResource.getTime());
                rm.setTypeFood(recipeResource.getTypeFood().toLowerCase());
                rm.setTitle(rt);

                rm.save();
                rm.refresh();

                ObjectNode jsonResult = Json.newObject();
                jsonResult.put("id", rm.getId());

                return Results.ok(msg+" "+ recipeResource.getName()+jsonResult);

            }

        }

    }

    public Result valorateRecipe(Http.Request req, Integer id) {

        Messages messages = messagesApi.preferred(req);

        Form<RecipeValoration> newRecipeValorationResource = formFactory.form(RecipeValoration.class).bindFromRequest(req);
        RecipeValoration recipeValoration;

        RecipeModel rm = RecipeModel.findById(Long.valueOf(id));

        if (rm == null) {
            return Results.notFound();

        }else{
            recipeValoration = newRecipeValorationResource.get();

            String msg = messages.at("puntuate");
            rm.addValoration(recipeValoration);

            rm.save();
            rm.refresh();

            ObjectNode jsonResult = Json.newObject();
            jsonResult.put("id", rm.getId());

            return Results.ok(msg+" "+ jsonResult);

        }

    }


    public Result inputIngredientsRecipe(Http.Request req, Integer id) {

        Messages messages = messagesApi.preferred(req);

        Form<RecipeIngredient> newIngredientForm = formFactory.form(RecipeIngredient.class).bindFromRequest(req);
        RecipeIngredient recipeIngredient;

        RecipeModel rm = RecipeModel.findById(Long.valueOf(id));

        if (rm == null) {
            return Results.notFound();

        }else{
            if(newIngredientForm.hasErrors()){
                return Results.badRequest(newIngredientForm.errorsAsJson());

            }else{

                recipeIngredient = newIngredientForm.get();
                rm.getIngredients().add(recipeIngredient);
                rm.save();
                rm.refresh();


                String msg = messages.at("ingredient");
                rm.addIngredient(recipeIngredient);

                ObjectNode jsonResult = Json.newObject();
                jsonResult.put("id", rm.getId());

                return Results.ok(msg+" "+ recipeIngredient.getNombreIngrediente()+jsonResult);

            }

        }

    }

    @Cached(key="recipe-view", duration = 5)
    public Result retrieveById(Http.Request req, Integer id) {

        Messages messages = messagesApi.preferred(req);

        RecipeModel rm;
        Optional<Object> optRecipe = cache.get("recipe");

        //Función cacheada, primero buscamos en la caché esa key por si se encuentra anteriormente, si no buscamos en el modelo
        //Si se realiza la operación antes de 5s de otra con esa key anterior se obtendrá el valor cacheado
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

        //Vista Json ó Xml(template) dependiendo del valor aceptado en la petición
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

        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

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

        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res;

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

        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

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

        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

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

        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

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

        List<RecipeResource> resourcesF = recipes
                .stream()
                .map(RecipeResource::new)
                .collect(Collectors.toList());

        JsonNode json = Json.toJson(resourcesF);
        Result res = Results.ok(json);

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
