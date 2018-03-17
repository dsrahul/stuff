package app.stuff;

public class Country {

    int id;
    String name;
    String continent;
    int population;

    public Country(int id, String name, String continent, int population) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }


}
