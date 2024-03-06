package com.lrr.Dieti23Server.repository;

import com.lrr.Dieti23Server.model.TestModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDAO extends CrudRepository<TestModel,Integer> {


}
