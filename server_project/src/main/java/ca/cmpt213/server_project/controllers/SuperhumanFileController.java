package ca.cmpt213.server_project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.cmpt213.server_project.models.Superhuman;
import ca.cmpt213.server_project.models.SuperhumanFile;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SuperhumanFileController {
    private SuperhumanFile superhumanRecords = new SuperhumanFile();

    @GetMapping("/api/superhuman/all")
    public List<Superhuman> retrieveAllSupers(HttpServletResponse response){
        System.out.println("GET /api/superhuman/all");
        response.setStatus(HttpServletResponse.SC_OK);
        return superhumanRecords.retrieveAllSupers();
    }

    @GetMapping("/api/superhuman/{id}")
    public Superhuman getSuper(@PathVariable int id, HttpServletResponse response){
        System.out.println("GET /api/superhuman/{id}");
        Superhuman requestedSuper = superhumanRecords.findSuper(id);
        if (requestedSuper != null){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return requestedSuper;
        
    }

    @PostMapping("/api/superhuman/add")
    public void addSuper(@RequestBody Superhuman newSuper, HttpServletResponse response){
        System.out.println("POST /api/superhuman/add");
        System.out.println(newSuper);
        response.setStatus(HttpServletResponse.SC_CREATED);
        superhumanRecords.addSuper(newSuper);
    }

    @DeleteMapping("/api/superhuman/{id}")
    public void deleteSuper(@PathVariable int id, HttpServletResponse response){
        System.out.println("DELETE /api/superhuman/{id}");
        boolean deletion_success = superhumanRecords.deleteSuper(id);
        if (deletion_success){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }       
    }

    
    
}
