package com.vote.voteservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MeetingAgendaResponse {

  @JsonProperty private String id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("createdDateTime")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime createdDateTime;

  @JsonProperty("durantionSecondsSession")
  private Integer durantionSecondsSession;

  @JsonProperty("endVoteSessionDateTime")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime endVoteSessionDateTime;

  @JsonProperty("startVoteSession")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime startVoteSession;

  @JsonProperty("votes")
  private List<VoteResponse> votes;
}
