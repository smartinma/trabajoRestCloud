package views;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.RecipeIngredient;
import models.RecipeModel;
import models.RecipeValoration;
import play.data.validation.Constraints;

import javax.validation.constraints.NotBlank;

public class RecipeInputResource extends RecipeResource {

    @Constraints.Min(1)
    @Constraints.Max(5)
    @JsonProperty("puntuation")
    private Integer puntuation;


    @JsonProperty("ingredients")
    private String ingredient;

    //Getters y setters
    public Integer getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(Integer puntuation) {
        this.puntuation = puntuation;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    //Conversion a modelo
    @Override
    public RecipeModel toModel() {
        RecipeModel u = super.toModel();
        RecipeValoration up = new RecipeValoration();
        RecipeIngredient ri = new RecipeIngredient();

        up.setPuntuation(this.puntuation);
        ri.setNombreIngrediente(this.ingredient);

        u.addValoration(up);
        u.addIngredient(ri);


        return u;
    }
}
