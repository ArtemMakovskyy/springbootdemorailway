package com.example.d2tech.service;

import com.example.d2tech.model.TestEntity;
import com.example.d2tech.repository.TestEntityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestEntityServisae {
    private final TestEntityRepository testEntityRepository;

    @PostConstruct
    public void start(){
        TestEntity testEntity = new TestEntity();
        testEntity.setId(2L);
        testEntity.setInfo("info 2");
        testEntityRepository.save(testEntity);

        TestEntity testEntity4 = new TestEntity();
        testEntity4.setId(4L);
        testEntity4.setInfo("info 42");
        testEntityRepository.save(testEntity4);
    }
}
