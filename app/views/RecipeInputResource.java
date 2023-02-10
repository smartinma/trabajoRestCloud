package views;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.RecipeModel;
import models.RecipeValoration;
import play.data.validation.Constraints;

public class RecipeInputResource extends RecipeResource {

    @Constraints.Min(0)
    @Constraints.Max(5)
    @JsonProperty("puntuation")
    private Integer puntuation;

    public Integer getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(Integer puntuation) {
        this.puntuation = puntuation;
    }

    @Override
    public RecipeModel toModel() {
        RecipeModel u = super.toModel();
        RecipeValoration up = new RecipeValoration();

        up.setPuntuation(this.puntuation);
        u.addValoration(up);


        return u;
    }
}
