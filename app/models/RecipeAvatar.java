package models;

import io.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RecipeAvatar extends Model{

    @OneToOne(mappedBy="avatar")
    private RecipeModel parentRecipe;

    private String url;

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

    public void setParentRecipe(RecipeModel parentUser) {

        this.parentRecipe = parentRecipe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
