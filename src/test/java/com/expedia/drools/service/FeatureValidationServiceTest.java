package com.expedia.drools.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.expedia.drools.config.Config;
import com.expedia.drools.model.Feature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class FeatureValidationServiceTest {

  @Autowired FeatureValidationService service;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testValidation() {
    List<Feature> features = new LinkedList<>();
    int total = 1000000;
    for (int i = 0; i < total; i++) {
      Feature feature = new Feature();
      feature.setId(RandomUtils.nextLong(0, 100));
      features.add(feature);


      if (i < 500) {
        feature.setCountry("CAN");
      }

      if (i >= 500 && i < 600) {
        feature.setName("Hotel Name " + i);
      }
    }



    for (int i = 0; i < 5; i++) {
      StopWatch stopWatch = new StopWatch();
      List<String> errors = service.validate(features, stopWatch);
      System.out.println(stopWatch.getTaskInfo()[0].getTimeMillis());
      //assertEquals(errors.size() ,total - 600);
    }



    List<String> errors = new ArrayList<>();
    //stopWatch.start("loop");
    features.forEach(f -> {
      if (StringUtils.isEmpty(f.getCountry())) {
        errors.add("INV_COUNTRY:" + f.getId());
      }
      if (StringUtils.isEmpty(f.getCountry())) {
      }
      if (StringUtils.isEmpty(f.getCountry())) {
      }
      if (StringUtils.isEmpty(f.getCountry())) {
      }
      if (StringUtils.isEmpty(f.getCountry())) {
      }

    });
    //stopWatch.stop();


    //System.out.println(stopWatch.getTaskInfo()[0].getTimeMillis());
    //System.out.println(stopWatch.getTaskInfo()[1].getTimeMillis());
  }
}