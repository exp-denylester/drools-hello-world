package com.expedia.drools.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.rule.Match;

import com.expedia.drools.model.Feature;

@Slf4j
public class TrackingAgendaEventListener extends DefaultAgendaEventListener {


  private List<Match> matchList = new ArrayList<Match>();

  @Override
  public void afterMatchFired(AfterMatchFiredEvent event) {
    Rule rule = event.getMatch().getRule();

    String ruleName = rule.getName();

    Object feature = event.getMatch().getObjects().stream().
        filter(o -> o instanceof Feature).collect(Collectors.toList()).get(0);

    matchList.add(event.getMatch());
    StringBuilder sb = new StringBuilder("Rule fired: " + ruleName);
    sb.append(" on " + feature);

    log.debug(sb.toString());
  }

}
