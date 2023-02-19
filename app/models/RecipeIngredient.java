package models;

import io.ebean.Model;
import jdk.jfr.Name;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeIngredient extends Model {

    @Id
    @Name("ingredient_id")
    private Long id;

    @ManyToMany(mappedBy = "ingredients")
    private List<RecipeModel> parentRecipe;


    @Constraints.Required
    @NotBlank
    String nombreIngrediente;

    public RecipeIngredient() {}

    public RecipeIngredient(List<RecipeModel> parentRecipe, String nombreIngrediente) {
        this.parentRecipe = parentRecipe;
        this.nombreIngrediente = nombreIngrediente;
    }

    public RecipeIngredient(String dbName, List<RecipeModel> parentRecipe, String nombreIngrediente) {
        super(dbName);
        this.parentRecipe = parentRecipe;
        this.nombreIngrediente = nombreIngrediente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RecipeModel> getParentRecipe() {
        return parentRecipe;
    }

    public void setParentRecipe(List<RecipeModel> parentRecipe) {
        this.parentRecipe = parentRecipe;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }
}
