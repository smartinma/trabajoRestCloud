package views;

import models.RecipeModel;
import models.RecipePassword;
import play.data.validation.Constraints;

import javax.validation.constraints.NotBlank;

public class RecipeInputResource extends RecipeResource {
    @Constraints.Required
    @NotBlank
    @Constraints.MinLength(8)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public RecipeModel toModel() {
        RecipeModel u = super.toModel();
        RecipePassword up = new RecipePassword();

        up.setPasswordHash(this.password);
        u.addPassword(up);


        return u;
    }
}
