package com.msdigischool.reactiveonboarding.controller.onboarding;

import com.msdigischool.reactiveonboarding.datalayer.communication.entity.TestMe2;
import com.msdigischool.reactiveonboarding.datalayer.communication.repository.Test2Repository;
import com.msdigischool.reactiveonboarding.datalayer.onboarding.entity.TestMe;
import com.msdigischool.reactiveonboarding.datalayer.onboarding.repository.TestRepository;
import com.msdigischool.reactiveonboarding.model.pojos.TestMeData;
import com.msdigischool.reactiveonboarding.service.CustomQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/onboarding")
public class Api {

    @Autowired
    TestRepository testRepository;

    @Autowired
    Test2Repository test2Repository;

    @Autowired
    CustomQueries customQueries;

    @GetMapping("/fetch")
    public CompletableFuture<List<TestMeData>> fetch(){
        return customQueries.getMeData();
    }



    @GetMapping("/test")
    public TestMe test(){
        return testRepository.save(TestMe.builder().nm("test").build());
    }


    @GetMapping("/test2")
    public TestMe2 test2(){
        return test2Repository.save(TestMe2.builder().nm("test").build());
    }

}
