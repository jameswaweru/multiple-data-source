package com.msdigischool.reactiveonboarding.service;

import com.msdigischool.reactiveonboarding.datalayer.communication.entity.TestMe2;
import com.msdigischool.reactiveonboarding.datalayer.onboarding.entity.TestMe;
import com.msdigischool.reactiveonboarding.model.pojos.TestMeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class CustomQueries {

    @Autowired
    @Qualifier("communicationJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Async
    public CompletableFuture<List<TestMeData>> getMeData(){
        String qry = "SELECT * FROM test_me";
        List<TestMeData> testMeDataList = new ArrayList<>();
        try {
           testMeDataList = jdbcTemplate.query(qry, (res, rows) ->
                           TestMeData.builder()
                                   .myNm(res.getString("nm"))
                                   .build()
                   );
        }catch (Exception exp){

        }
        return CompletableFuture.completedFuture(testMeDataList);
    }

}
