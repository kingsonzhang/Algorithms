import java.util.ArrayList;

public class Graph{
    private ArrayList<City> mapOfCities;

    public Graph(){
        this.mapOfCities = new ArrayList<>();
    }

    //Getter Methods
    public ArrayList<City> getMap() {return this.mapOfCities;}

    public City findCity(String name){
        for (int i = 0; i < this.mapOfCities.size(); i++)
            if (this.mapOfCities.get(i).getCityName().equals(name))
                return this.mapOfCities.get(i);
        return null;
    }

    public int maxIslands(){
        int count = 0;
        ArrayList<String> visitedCities = new ArrayList<>();
        for (int i = 0; i < this.mapOfCities.size(); i++){
            if (!visitedCities.contains(this.mapOfCities.get(i).getCityName())){
                this.traverseAdjacentCities(this.mapOfCities.get(i), visitedCities);
                count++;
            }
        }
        return count;
    }

    private void traverseAdjacentCities(City city, ArrayList<String> traversed){
        int index = traversed.size();
        traversed.add(city.getCityName());
        while(index < traversed.size()){
            ArrayList<String> adjacentCities = this.findCity(traversed.get(index)).getAdjacentCities();
            for (int i = 0; i < adjacentCities.size(); i++){
                if (!traversed.contains(adjacentCities.get(i)))
                    traversed.add(adjacentCities.get(i));
            }
            index++;
        }
    }

    public ArrayList<String> archipelagoPopulation(){
        ArrayList<String> islandPopulationCount = new ArrayList<>();
        ArrayList<String> visitedCities = new ArrayList<>();
        for (int i = 0; i < this.mapOfCities.size(); i++){
            if (!visitedCities.contains(this.mapOfCities.get(i).getCityName())){
                int index = visitedCities.size();
                int population = 0;
                this.traverseAdjacentCities(this.mapOfCities.get(i), visitedCities);
                for (;index < visitedCities.size(); index++)
                    population += this.findCity(visitedCities.get(index)).getPopulation();
                islandPopulationCount.add(String.format("%s Archipelago: %s", this.mapOfCities.get(i).getCityName(), population));
            }
        }
        return islandPopulationCount;
    }

    public int uniqueHighwaysPath(String startingCity, String endingCity){
        if (this.findCity(startingCity) == null || this.findCity(endingCity) == null)
            return -1;
        ArrayList<String> path = new ArrayList<>();
        path.add(startingCity);
        int index = 0;
        while(!path.contains(endingCity)){
            if (this.findCity(path.get(index)).getAdjacentCities().contains(endingCity))
                path.add(endingCity);
            else
                this.findCity(path.get(index)).getAdjacentCities().forEach(city -> path.add(city));
            index++;
        }
        return (int)Math.ceil(Math.log(path.size() + 1) / Math.log(2)) - 1;
    }

    //Setter Methods
    public void addCity(City newCity){
        this.mapOfCities.add(newCity);
    }
}