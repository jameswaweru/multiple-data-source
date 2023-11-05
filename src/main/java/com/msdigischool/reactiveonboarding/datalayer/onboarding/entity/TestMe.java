package com.msdigischool.reactiveonboarding.datalayer.onboarding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "test_me")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestMe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String nm;

//    private String nmm;
}
