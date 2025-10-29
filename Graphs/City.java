import java.util.ArrayList;

public class City{
    private String name;
    private int population;
    private ArrayList<String> adjacentCities;

    public City(){
        this.name = "";
        this.population = 0;
        this.adjacentCities = new ArrayList<>();
    }

    public City(String data){
        String[] split = data.split(":");
        this.name = split[0].trim();
        this.population = Integer.parseInt(split[1].trim());
        this.adjacentCities = new ArrayList<>();
    }

    //Getter Methods
    public String getCityName() {return this.name;}

    public int getPopulation() {return this.population;}

    public ArrayList<String> getAdjacentCities() {return this.adjacentCities;}

    //Setter Methods
    public void addAdjacentCity(String city) {this.adjacentCities.add(city);}

    public void addPopulation(int people) {this.population += people;}
}