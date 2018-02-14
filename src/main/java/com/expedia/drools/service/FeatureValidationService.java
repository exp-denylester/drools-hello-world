package com.expedia.drools.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.expedia.drools.model.Feature;
import com.expedia.drools.model.FeatureValidationRequest;
import com.expedia.drools.model.FeatureValidationResponse;

@Service
public class FeatureValidationService {

  @Autowired
  private KieContainer kieContainer;

  public List<String> validate(List<Feature> features) {
    KieSession kieSession = kieContainer.newKieSession();
    FeatureValidationResponse response = new FeatureValidationResponse();
    kieSession.setGlobal("response", response);
    kieSession.insert(new FeatureValidationRequest(features));
    kieSession.fireAllRules();
    kieSession.dispose();
    return response.getErrors();
  }
}
