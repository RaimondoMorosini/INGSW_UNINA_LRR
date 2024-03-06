package com.lrr.Dieti23Server.service;

import com.lrr.Dieti23Server.model.TestModel;
import com.lrr.Dieti23Server.repository.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestService{


    private TestDAO testDao;
    private List<TestModel> list;

    public TestService(){

        list = new ArrayList<TestModel>();

        list.add(new TestModel(1,"prima stringa di prova"));
        list.add(new TestModel(2,"seconda stringa di prova"));
        list.add(new TestModel(3,"terza stringa di prova"));

    }

    public Iterable<TestModel> getAllTestModel(){

        return list;
    }

    public Optional<TestModel> getSingleTestModel(@PathVariable int primaryKey){

        Optional<TestModel> testModel = list.stream().filter(item->item.getId() == primaryKey).findFirst();

        if(testModel.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return testModel;

    }

    public TestModel createTestModel(TestModel testModelCreato){

        list.add(testModelCreato);

        return testModelCreato;
    }

    public Optional<TestModel> update(int primaryKeyDaModificare, TestModel testModelModificato){

        Optional<TestModel> testModelTrovato = list.stream().filter(item->item.getId() == primaryKeyDaModificare).findFirst();

        if(testModelTrovato.isEmpty()){

            return Optional.empty();
        }

        testModelTrovato.get().setStringa(testModelModificato.getStringa());

        return testModelTrovato;

    }

    public Boolean delete(int primaKeyDaEliminare){

        Optional<TestModel> testModelTrovato = list.stream().filter(item->item.getId() == primaKeyDaEliminare).findFirst();

        if(testModelTrovato.isEmpty()) return false;

        list.remove(testModelTrovato.get());

        return true;
    }
}
