package com.j.s.galley.program;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CucumberSpringConfig {
    @InjectMocks
    protected MicroServiceLearningApplication microServiceApp;
    @Mock
    protected IActorRepository actorRepository;
    @Mock
    protected IFilmRepository filmRepository;
    @Mock
    protected ILanguageRepository languageRepository;
    @Mock
    protected ICategoryRepository categoryRepository;
}
