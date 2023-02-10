package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class RecipeTitle extends Model{

    @OneToOne(mappedBy = "title")
    public RecipeModel parentRecipe;

    @Constraints.Required
    @NotBlank
    public String title;
    @Id
    private Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RecipeModel getParentRecipe() {
        return parentRecipe;
    }
    public void setParentRecipe(RecipeModel parentRecipe) {
        this.parentRecipe = parentRecipe;
    }
}
