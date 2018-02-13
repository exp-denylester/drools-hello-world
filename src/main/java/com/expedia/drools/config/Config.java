package com.expedia.drools.config;

import java.util.Set;
import java.util.regex.Pattern;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.expedia.drools.service")
public class Config {

  @Bean
  public KieContainer kieContainer() {
    KieServices kieServices = KieServices.Factory.get();

    Reflections reflections = new Reflections("", new ResourcesScanner());
    Set<String> rules = reflections.getResources(Pattern.compile(".*\\.drl"));

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    rules.forEach(f -> kieFileSystem.write(ResourceFactory.newClassPathResource(f)));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();

    if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
      throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
    }

    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }
}
