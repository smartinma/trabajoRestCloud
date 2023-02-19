package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class RecipeValoration extends Model {

    //Relación 1-N receta-valoraciones
    @ManyToOne()
    public RecipeModel parentRecipe;

    public Integer puntuation;

    @Id
    private Long id;

    //Getters y setters
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public RecipeModel getParentRecipe() {
        return parentRecipe;
    }

    public void setParentRecipe(RecipeModel parentRecipe) {
        this.parentRecipe = parentRecipe;
    }

    public Integer getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(Integer puntuation) {
        this.puntuation = puntuation;
    }

}