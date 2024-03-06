package com.lrr.Dieti23Server.service;

import com.lrr.Dieti23Server.model.TestModel;

import java.util.Optional;

public interface ITestService {
    public Iterable<TestModel> getAll();

    public Optional<TestModel> getById(int id);

    public TestModel create(TestModel photo);

    public Optional<TestModel> update(int id, TestModel photo);

    public Boolean delete(int id);

}
