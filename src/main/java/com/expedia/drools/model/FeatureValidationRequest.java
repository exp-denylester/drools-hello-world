package com.expedia.drools.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeatureValidationRequest {

  private List<Feature> features;
}
