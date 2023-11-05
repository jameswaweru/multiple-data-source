package com.msdigischool.reactiveonboarding.datalayer.communication.repository;

import com.msdigischool.reactiveonboarding.datalayer.communication.entity.TestMe2;
import com.msdigischool.reactiveonboarding.datalayer.onboarding.entity.TestMe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Test2Repository extends JpaRepository<TestMe2, Integer> {
}
