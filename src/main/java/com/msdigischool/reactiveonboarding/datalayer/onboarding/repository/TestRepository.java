package com.msdigischool.reactiveonboarding.datalayer.onboarding.repository;

import com.msdigischool.reactiveonboarding.datalayer.onboarding.entity.TestMe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestMe, Integer> {
}
