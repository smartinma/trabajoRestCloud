package models;


import io.ebean.Finder;
import io.ebean.Model;
import jdk.jfr.Name;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class RecipeModel extends Model {
    private static final Finder<Long,RecipeModel> find = new Finder<>(RecipeModel.class);

    @Id
    @Name("recipe_id")
    public Long id;

    /*PARAMETROS DE LA RECETA:
        -Nombre General: nombre general que se le da a la receta, por ejemplo cocido.
        -Tipo de Receta: tipo de receta para el consumidor, si es vegana, vegetariana, con alergenos...etc
        -Tiempo de Preparación: tiempo máximo estimado de preparación de la receta
        *Relaciones:
            -Título único (1-1): nombre único de la receta, no hay dos iguales, por ejemplo cocido madrileño.
            -Valoraciones (1-N): valoraciones/puntuaciones de la receta
            -Ingredientes (N-M): ingredientes que componen las recetas, ingredientes únicos pero comunes
     */

    public String name;
    public Integer time;
    public String typeFood;

    //Relación 1-1, cada receta tiene un único título
    @OneToOne(cascade = CascadeType.ALL)
    public RecipeTitle title;

    //Relación 1-N, cada receta tiene valoraciones unicas en cada receta
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parentRecipe")
    public List<RecipeValoration> valorations;

    //Relación N-M, cada receta tiene ingredientes que pueden ser comunes con otras recetas
    @ManyToMany(cascade = CascadeType.ALL)
    private List<RecipeIngredient> ingredients;

    //Constructores
    public RecipeModel(){}

    public RecipeModel(Long id, String name, Integer time, String typeFood, RecipeTitle title, List<RecipeValoration> valorations, List<RecipeIngredient> ingredients) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.typeFood = typeFood;
        this.title = title;
        this.valorations = valorations;
        this.ingredients = ingredients;
    }

    public RecipeModel(String dbName, Long id, String name, Integer time, String typeFood, RecipeTitle title, List<RecipeValoration> valorations, List<RecipeIngredient> ingredients) {
        super(dbName);
        this.id = id;
        this.name = name;
        this.time = time;
        this.typeFood = typeFood;
        this.title = title;
        this.valorations = valorations;
        this.ingredients = ingredients;
    }

    //Metodos de acceso (base de datos) - Búsquedas
    //Por ID
    public static RecipeModel findById(Long id) {
        return find.byId(id);

    }

    //Por NOMBRE
    public static List<RecipeModel> findByName(String name) {

        return find.query()
                .where()
                .eq("name", name.toLowerCase())
                .findList();
    }

    //Por TITULO
    public static RecipeModel findByTitle(String recipeTitle) {

        return find.query()
                .where()
                .eq("title.title", recipeTitle.toLowerCase())
                .orderBy("title")
                .findOne();
    }

    //Por TIPO
    public static List<RecipeModel> findByType(String typeFood) {

        return find.query()
                .where()
                .eq("typeFood", typeFood.toLowerCase())
                .orderBy("id")
                .findList();
    }

    //Por TIEMPO DE PREPARACION MAXIMO
    public static List<RecipeModel> findByTime(Integer time) {

        return  find.query()
                .where()
                .le("time", time)
                .orderBy("time")
                .findList();
    }

    //Por PUNTUACIÓN
    public static List<RecipeModel> findByValoration(Integer point) {

        return find.query()
                .fetch("valorations")
                .where()
                .ge("valorations.puntuation", point)
                .orderBy("name")
                .findList();
    }


    //Por INGREDIENTE
    public static List<RecipeModel> findByIngredient(String ingredient) {

        return find.query()
                .fetch("ingredients")
                .where()
                .eq("ingredients.nombreIngrediente", ingredient.toLowerCase())
                .orderBy("name")
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

    //Getters y setters
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public RecipeTitle getTitle() {
        return title;
    }

    public void setTitle(RecipeTitle title) {
        this.title = title;
        title.setParentRecipe(this);
    }

    public List<RecipeValoration> getValorations() {
        return valorations;
    }

    public void setValorations(List<RecipeValoration> valorations) {
        this.valorations = valorations;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }


    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
       this.ingredients = ingredients;
    }

    //Metodos de adicción de relacion/adiccion de modelos
    public void addValoration(RecipeValoration recipeValoration){
        if(this.valorations == null){
            this.valorations = new ArrayList<>();
        }

        this.valorations.add(recipeValoration);
        recipeValoration.setParentRecipe(this);
    }


    public void addIngredient(RecipeIngredient recipeIngredient){
        ArrayList<RecipeModel> tmp = new ArrayList<>();
        tmp.add(this);
        if(this.ingredients == null){
            this.ingredients = new ArrayList<>();
        }

        this.ingredients.add(recipeIngredient);
        recipeIngredient.setParentRecipe(tmp);
    }

}
