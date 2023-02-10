package views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import models.RecipeAvatar;
import models.RecipeModel;
import org.hibernate.validator.constraints.URL;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.validation.constraints.NotBlank;


public class RecipeResource {
    @JsonProperty("nombre")
    @Constraints.Required
    @NotBlank(message="Nombre vacío")
    private String name;

    @Constraints.Required
    @Constraints.Min(18)
    private Integer age;

    @URL
    @Constraints.Required
    @JsonProperty("avatar_url")
    private String avatarUrl;


    //Contructores

    public RecipeResource(){
        super();
    }
    public RecipeResource(RecipeModel recipeModel){
        super();


        this.name = recipeModel.getName();
        this.age = recipeModel.getAge();

        RecipeAvatar ua = recipeModel.getAvatar();

        if(ua != null){
            this.avatarUrl= ua.getUrl();
        }
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

    public JsonNode toJson() {

        return Json.toJson(this);

    }

    public RecipeModel toModel() {

        RecipeModel u = new RecipeModel();
        RecipeAvatar ua = new RecipeAvatar();

        u.setName(this.name);
        u.setAge(this.age);
        ua.setUrl(this.avatarUrl);

        u.setAvatar(ua);

        return u;

    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}


