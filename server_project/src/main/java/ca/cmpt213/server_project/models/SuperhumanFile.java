package ca.cmpt213.server_project.models;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class SuperhumanFile {
    private static final String SUPERHUMAN_FILE_PATH = "server_project/data/superhumans.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static int id_counter;

    private List<Superhuman> readFromFile() {
        try (FileReader reader = new FileReader(SUPERHUMAN_FILE_PATH)) {
            return gson.fromJson(reader, new TypeToken<List<Superhuman>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeToFile(List<Superhuman> superhumans) {
        try (FileWriter writer = new FileWriter(SUPERHUMAN_FILE_PATH)) {
            gson.toJson(superhumans, writer);
        } catch (IOException e) {
            // Handle exception, e.g., unable to write to file
            e.printStackTrace();
        }
    }


    public List<Superhuman> retrieveAllSupers(){
        List<Superhuman> superhumans = readFromFile();
        return superhumans;
    }


    public Superhuman findSuper(int id){
        List<Superhuman> superhumans = readFromFile();
        for(Superhuman superhuman : superhumans){
            if(superhuman.getId() == id){
                return superhuman;
            }
        }
        return null;
    }

    public void addSuper(Superhuman superhuman){
        List<Superhuman> superhumans = readFromFile();
        if (superhumans.size() != 0){
            id_counter = (superhumans.get(superhumans.size() - 1)).getId() + 1;
        } else {   
            id_counter = 0;
        }
        superhuman.setId(id_counter);
        superhumans.add(superhuman);
        writeToFile(superhumans);
    }

    public boolean deleteSuper(int id){
        List<Superhuman> superhumans = readFromFile();
        boolean deletion_success = false;
        for(int i = 0; i < superhumans.size(); i++){
            if(superhumans.get(i).getId() == id){
                deletion_success = true;
                superhumans.remove(i);
                break;
            }
        }
        writeToFile(superhumans);
        return deletion_success;
    }
}
