package views;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.RecipeIngredient;
import models.RecipeModel;
import models.RecipeValoration;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.i18n.MessagesApi;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class RecipeInputResource extends RecipeResource {

    @Constraints.Min(0)
    @Constraints.Max(5)
    @JsonProperty("puntuation")
    private Integer puntuation;


    @JsonProperty("ingredients")
    private String ingredient;

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
