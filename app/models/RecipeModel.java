package models;


import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeModel extends Model {
    private static final Finder<Long,RecipeModel> find = new Finder<>(RecipeModel.class);

    @Id
    private Long id;

    private String name;
    private Integer age;
    private String type;
    private Integer time;


    @OneToOne(cascade = CascadeType.ALL)
    private RecipeAvatar avatar;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parentRecipe")
    private List<RecipePassword> passwords;


    //Metodos de acceso (base de datos) - Búsquedas
    //Por ID
    public static RecipeModel findById(Long id) {
        return find.byId(id);

    }

    //Por NOMBRE
    public static RecipeModel findByName(String name) {

        return find.query()
                .where()
                .eq("name", name)
                .findOne();
    }

    /*
    //Por TIPO (vegano, vegetariano, carnivoro, omnivoro...)
    public static RecipeModel findByType(String type) {

        return find.query()
                .where()
                .eq("type", type)
                .findOne();
    }

    //Por TIEMPO DE PREPARACION MAXIMO
    public static RecipeModel findByTime(Integer time) {

        return find.query()
                .where()
                .le("time", time)
                .orderBy("name")
                .setMaxRows(25)
                .setFirstRow(0)
                .findList();
    }
    */


    public static List<RecipeModel> findGreaterThanAge(Integer age) {

        return find.query()
                .where()
                .gt("age", age)
                .orderBy("name")
                .setMaxRows(25)
                .setFirstRow(0)
                .findList();

    }

    //TODAS las recetas
    public static List<RecipeModel> findAll() {
        //WARNING: Esto no debería hacerse
        return find.all();

    }

    //ELIMINADO de receta
    public static void delete(Integer id) {
        find.deleteById(Long.valueOf(id));
    }

    //Getter y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RecipeAvatar getAvatar() {
        return avatar;
    }

    public void setAvatar(RecipeAvatar avatar) {
        this.avatar = avatar;
        avatar.setParentRecipe(this);
    }

    public List<RecipePassword> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<RecipePassword> passwords) {
        this.passwords = passwords;
    }

    public void addPassword(RecipePassword recipePassword){
        if(this.passwords == null){
            this.passwords = new ArrayList<>();
        }

        this.passwords.add(recipePassword);
        recipePassword.setParentRecipe(this);
    }
}
