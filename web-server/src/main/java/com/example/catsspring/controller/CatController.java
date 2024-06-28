package com.example.catsspring.controller;
import com.example.catsspring.model.Cat;
import com.example.catsspring.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cats")
public class CatController {

    private final CatRepository catRepository;

    @Autowired
    public CatController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCatById(@PathVariable Long id) {
        Optional<Cat> catOptional = catRepository.findById(id);

        if (catOptional.isPresent()) {
            return ResponseEntity.ok(catOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat not found with id: " + id);
        }
    }
    @PostMapping("/")
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createdCat = catRepository.save(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCat);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        if (!catRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat not found with id: " + id);
        }
        cat.setId(id);
        Cat updatedCat = catRepository.save(cat);
        return ResponseEntity.ok(updatedCat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        if (!catRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat not found with id: " + id);
        }
        catRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Cat>> getAllCats() {
        List<Cat> cats = catRepository.findAll();
        return ResponseEntity.ok(cats);
    }

}