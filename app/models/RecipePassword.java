package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RecipePassword extends Model {


    @ManyToOne
    private RecipeModel parentRecipe;

    private String passwordHash;

    @Id
    private Long id;

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}