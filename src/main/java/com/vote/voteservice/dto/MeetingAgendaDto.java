package com.vote.voteservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MeetingAgendaDto {

  private String id;
  private String title;
  private String description;
  private LocalDateTime createdDateTime;
  private int durantionSecondsSession;
  private LocalDateTime endVoteSessionDateTime;
  private LocalDateTime startVoteSession;
  private List<VoteDto> voteDtoList;

  public int getDurantionSecondsSession() {
    if (Objects.isNull(durantionSecondsSession) || durantionSecondsSession < 60) {
      return 60;
    }
    return durantionSecondsSession;
  }
}
