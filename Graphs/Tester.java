import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester{
    public static void main(String args[]){
        try{
            Scanner cityFile = new Scanner(new File("./Graphs/city_population.txt"));
            Graph map = new Graph();
            while (cityFile.hasNext()){
                String data = cityFile.nextLine();
                String[] split = data.split(":");
                City repeat = map.findCity(split[0].trim());
                if (repeat != null)
                    repeat.addPopulation(Integer.parseInt(split[1].trim()));
                else
                    map.addCity(new City(data));
            }

            Scanner roadFile = new Scanner(new File("./Graphs/road_network.txt"));
            while (roadFile.hasNext()){
                String[] split = roadFile.nextLine().split(":");
                City firstCity = map.findCity(split[0].trim());
                City secondCity = map.findCity(split[1].trim());
                if (firstCity != null && secondCity != null){
                    firstCity.addAdjacentCity(secondCity.getCityName());
                    secondCity.addAdjacentCity(firstCity.getCityName());
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}