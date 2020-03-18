package com.transportsystem.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transportsystem.backend.embeddable.Comment;
import com.transportsystem.backend.model.Nomenclature;
import com.transportsystem.backend.service.JsonService;
import com.transportsystem.backend.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class NomenclatureRESTController {
    private NomenclatureService nomenclatureService;
    private JsonService jsonService;

    @Autowired
    public void setNomenclatureService(NomenclatureService nomenclatureService) {this.nomenclatureService = nomenclatureService;}

    @Autowired
    public void setJsonService(JsonService jsonService) {this.jsonService = jsonService;}

    @GetMapping("/nomenclatures")
    public List<Nomenclature> readAllNomenclatures() {
        List<Nomenclature> nomenclatures = nomenclatureService.getAllNomenclatures();
        return nomenclatures;
    }

    @GetMapping("nomenclatures/{id}")
    public Nomenclature readNomenclature(@PathVariable int id) {
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(id);
        return nomenclature;
    }

    @PostMapping(value = "nomenclatures/add", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Nomenclature createNomenclature(@RequestBody String data) throws JsonProcessingException {
        Nomenclature nomenclature = jsonService.getObjectMapper().readValue(data,Nomenclature.class);
        nomenclatureService.add(nomenclature);
        return nomenclature;
    }

    @PutMapping(value = "nomenclatures/edit", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Nomenclature updateNomenclature(@RequestBody String data) throws JsonProcessingException {
        Comment comments = nomenclatureService.getNomenclatureById(jsonService.getValueFromJson(data,"id").asInt()).getComments();
        comments.addComment(jsonService.getCommentFromJson(data));
        Nomenclature nomenclature = jsonService.getObjectMapper().readValue(data,Nomenclature.class);
        nomenclature.setComments(comments);
        nomenclatureService.edit(nomenclature);
        return nomenclature;
    }

    @DeleteMapping("nomenclatures/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNomenclature(@PathVariable int id) {
        Nomenclature nomenclature = nomenclatureService.getNomenclatureById(id);
        nomenclatureService.delete(nomenclature);
    }
}
