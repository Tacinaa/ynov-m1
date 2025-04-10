package org.example.springdatam1.controller;


import jakarta.validation.Valid;
import org.example.springdatam1.entity.Publisher;
import org.example.springdatam1.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {




    @Autowired
    private PublisherService publisherService;


    @GetMapping
    public List<Publisher> getAllGenres(){
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getGenreById(@PathVariable("id") Long id){
        Publisher publisher = publisherService.findById(id);
        if( publisher!= null){
            return ResponseEntity.status(HttpStatus.OK).body(publisher);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updateGenre(@PathVariable Long id,@Valid @RequestBody Publisher publisher){
        try{
            Publisher publisher1 = publisherService.update(id,publisher);
            if(publisher1!= null){
                return ResponseEntity.ok(publisher1);
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<Publisher> createGenre(@Valid @RequestBody Publisher publisher){
        try{
            Publisher publisher1 = publisherService.save(publisher);
            return ResponseEntity.ok(publisher1);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){
        try{
            publisherService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }

    }




}

