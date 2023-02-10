package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class RecipeIngredient extends Model {

    //@ManyToMany(mappedBy = "ingredients")
    //public Set<RecipeModel> parentRecipe;

    @Constraints.Required
    @NotBlank
    String nombreIngrediente;

    //public Set<RecipeModel> getParentRecipe() {
    //    return parentRecipe;
    //}

    //public void setParentRecipe(Set<RecipeModel> parentRecipe) {
    //    this.parentRecipe = parentRecipe;
    //}

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }
}
