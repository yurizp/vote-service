package com.vote.voteservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteResponse {

  @JsonProperty("acceptMeetingAgenda")
  private Boolean approved;

  @JsonProperty("associatedId")
  private String associatedId;
}
