package com.lrr.Dieti23Server.controller.api;

import com.lrr.Dieti23Server.model.TestModel;
import com.lrr.Dieti23Server.service.ITestService;
import com.lrr.Dieti23Server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@CrossOrigin
public class TestModelController {

    @Autowired
    @Qualifier("provaEtichetta")
    private ITestService testService;

    @RequestMapping("/testModel/api")
    public Iterable<TestModel> getAll(){

        return testService.getAll();
    }

    @RequestMapping("/testModel/api/{chiavePrimaria}")
    public TestModel getChiave(@PathVariable int chiavePrimaria){

        Optional<TestModel> modello = testService.getById(chiavePrimaria);

        return modello.get();
    }

    @RequestMapping(value="/testModel/api", method = RequestMethod.POST)
    public TestModel create(@RequestBody TestModel modello){

        return testService.create(modello);
    }

    @RequestMapping(value="/testModel/api/{chiave}", method = RequestMethod.PUT)
    public TestModel update(@PathVariable int chiave, @RequestBody TestModel modello){

        Optional<TestModel> updateModello = testService.update(chiave,modello);

        if(updateModello.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item non trovato");
        }

        return updateModello.get();
    }

    @RequestMapping(value="/testModel/api/{chiave}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int chiave){

        Boolean isDeleted = testService.delete(chiave);

        if(!isDeleted){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item non trovato");
        }
    }
}
