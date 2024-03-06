package com.lrr.Dieti23Server.service;

import com.lrr.Dieti23Server.model.TestModel;
import com.lrr.Dieti23Server.repository.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service("provaEtichetta")
public class implementazioneTestDAO implements ITestService{

    @Autowired
    private TestDAO modelRepository;

    @Override
    public Iterable<TestModel> getAll() {

        return modelRepository.findAll();
    }

    @Override
    public Optional<TestModel> getById(int id) {

        return modelRepository.findById(id);
    }

    @Override
    public TestModel create(TestModel modello) {

        return modelRepository.save(modello);
    }

    @Override
    public Optional<TestModel> update(int id, TestModel modello) {

        Optional<TestModel> foundModel = modelRepository.findById(id);

        if (foundModel.isEmpty()) {

            return Optional.empty();
        }

        foundModel.get().setStringa(modello.getStringa());

        modelRepository.save(foundModel.get());

        return foundModel;
    }

    @Override
    public Boolean delete(int id) {

        Optional<TestModel> foundModel = modelRepository.findById(id);

        if (foundModel.isEmpty()) {

            return false;
        }

        modelRepository.delete(foundModel.get());

        return true;
    }

}
