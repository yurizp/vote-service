package com.vote.voteservice.controller.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VoteInput {

  @JsonProperty("associatedId")
  private String associatedId;

  @JsonProperty("acceptMeetingAgenda")
  private boolean acceptMeetingAgenda;
}
