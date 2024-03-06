package com.lrr.Dieti23Server.controller.api;

import com.lrr.Dieti23Server.dto.ReqRes;
import com.lrr.Dieti23Server.model.TestModel;
import com.lrr.Dieti23Server.repository.TestDAO;
import com.lrr.Dieti23Server.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminApiController {

    @Autowired
    private TestDAO testRepo;

    @GetMapping("/public/product")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(testRepo.findAll());
    }

    @PostMapping("/admin/saveproduct")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes testRequest){
        TestModel testToSave = new TestModel();
        testToSave.setStringa(testRequest.getName());
        return ResponseEntity.ok(testRepo.save(testToSave));
    }


    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("USers alone can access this ApI only");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminaAndUsersApi(){
        return ResponseEntity.ok("Both Admin and Users Can  access the api");
    }
}
