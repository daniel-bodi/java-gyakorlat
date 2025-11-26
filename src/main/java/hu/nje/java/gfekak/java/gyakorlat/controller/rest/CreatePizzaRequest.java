package hu.nje.java.gfekak.java.gyakorlat.controller.rest;

/**
 * @author danielbodi
 */
public class CreatePizzaRequest {
    private String name;
    private boolean vegetarian;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
