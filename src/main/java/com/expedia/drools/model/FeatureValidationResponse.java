package com.expedia.drools.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FeatureValidationResponse {

  List<String> errors = new ArrayList<>();

  public void addError(String s) {
    errors.add(s);
  }
}
