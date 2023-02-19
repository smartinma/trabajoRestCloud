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

    @JsonProperty("name")
    @Constraints.Required
    @NotBlank
    private String name;

    @JsonProperty("time")
    @Constraints.Required
    @Constraints.Min(1)
    private Integer time;

    @JsonProperty("title")
    @Constraints.Required
    @NotBlank
    private String title;

    @JsonProperty("typeFood")
    @Constraints.Required
    @NotBlank
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
            this.title = rt.getTitle();
        }


    }


    public JsonNode toJson() {

        return Json.toJson(this);

    }

    public RecipeModel toModel() {

        RecipeModel rm = new RecipeModel();
        RecipeTitle rt = new RecipeTitle();

        rm.setName(this.name.toLowerCase());
        rm.setTime(this.time);
        rm.setTypeFood(this.typeFood.toLowerCase());

        rt.setTitle(this.title.toLowerCase());
        rm.setTitle(rt);

        return rm;

    }
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


