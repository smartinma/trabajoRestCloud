package models;

import io.ebean.Finder;
import io.ebean.Model;
import jdk.jfr.Name;
import org.checkerframework.common.aliasing.qual.Unique;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeIngredient extends Model {

    private static final Finder<Long,RecipeIngredient> finder = new Finder<>(RecipeIngredient.class);

    @Id
    @Name("ingredient_id")
    private Long id;

    //Relacion N-M receta-ingrediente
    @ManyToMany(mappedBy = "ingredients")
    private List<RecipeModel> parentRecipe;

    //Nombre del ingrediente
    @Constraints.Required
    @NotBlank(message = "blank")
    String nombreIngrediente;

    //Constructores
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

    //Getters y Setters
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

    public static RecipeIngredient findByIngredientName(String ingredientName) {

        return finder.query()
                .where()
                .eq("nombreIngrediente", ingredientName.toLowerCase())
                .orderBy("id")
                .findOne();
    }
}
