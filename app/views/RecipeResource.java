package views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import models.RecipeIngredient;
import models.RecipeTitle;
import models.RecipeModel;
import models.RecipeValoration;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;


public class RecipeResource {

    //Parámetros de la receta: nombre, tiempo maximo de elaboración, titulo y tipo de receta
    @JsonProperty("name")
    @Constraints.Required(message = "required")
    @NotBlank(message = "blank")
    private String name;

    @JsonProperty("time")
    @Constraints.Required(message = "required")
    @Constraints.Min(1)
    private Integer time;

    @JsonProperty("title")
    @Constraints.Required(message = "required")
    @NotBlank(message = "blank")
    private String title;

    @JsonProperty("typeFood")
    @Constraints.Required(message = "required")
    @NotBlank(message = "blank")
    private String typeFood;


    //Contructores
    public RecipeResource(){
        super();
    }
    public RecipeResource(RecipeModel recipeModel){
        super();


        this.name = recipeModel.getName();
        this.time = recipeModel.getTime();
        this.typeFood = recipeModel.getTypeFood();

        RecipeTitle rt = recipeModel.getTitle();

        if(rt != null){
            this.title = rt.getTitle().replaceAll(" ","_");
        }


    }

    //Conversion a JSON
    public JsonNode toJson() {

        return Json.toJson(this);

    }

    //Conversion a modelo
    public RecipeModel toModel() {

        RecipeModel rm = new RecipeModel();
        RecipeTitle rt = new RecipeTitle();

        rm.setName(this.name.toLowerCase());
        rm.setTime(this.time);
        rm.setTypeFood(this.typeFood.toLowerCase());

        rt.setTitle(this.title.toLowerCase().replaceAll(" ","_"));
        rm.setTitle(rt);

        return rm;

    }

    //Getters y setters
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getTime() {

        return time;
    }

    public void setTime(Integer time) {

        this.time = time;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }
}


