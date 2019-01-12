package com.solar.guru.spring5recipeproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int prepTime;
    private int cookTime;
    private int servings;
    private String source;
    private String url;
    private String directions;
    //private Difficulty difficulty;

    /*
    @Log annotation specifies that this value can be very large.
    In this case, JPA will store BLOB (binary large object) field in database (because it's Byte)
     */
    @Lob
    private Byte[] image;

    /*
    EnumType.STRING overrides default behaviour ORDINAL. ORDINAL causes that integers are stored in db (1,2,3). STRING
    will force to store Strings in db (EASY, MODERATE, HARD). It's important because if you have ORDINAL type and you
    will add new Enum value in different place than at the end, it will cause that the order will change and 3 will become
    4 and 2 will become 3. It can be confusing.
     */
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    /*
    @JoinTable is used to specify names of joining table and its columns
    @mappedBy on the non-owning side will cause that only one joining table will be created. Without it, there will be
    two different tables containing the same relationship.
     */
    @ManyToMany
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToOne (cascade = CascadeType.ALL)
    private Note note;

    /*
    CascadeType.ALL means that when Recipe will be deleted, its Ingredients also will be deleted
    mappedBy - "recipe" means that on the child (Ingredient) this relationship will be stored in "recipe" property
    This relationship is bidirectional (in Ingredient there's @ManyToOne annotation).
    In db, RECIPE_ID column will appear in INGREDIENT table. In RECIPE table there will be no column for that relationship.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

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

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
