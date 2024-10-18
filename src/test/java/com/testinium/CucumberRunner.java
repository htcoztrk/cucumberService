package com.testinium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features",
        plugin = { "pretty", "junit:target/cucumber-reports/testNGReport.xml" },
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}

