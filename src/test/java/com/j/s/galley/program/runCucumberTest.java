package com.j.s.galley.program;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty"},
    features = "src/test/resources/Cucumber",
    glue = "com.j.s.galley.program"
)
public class runCucumberTest {

}
