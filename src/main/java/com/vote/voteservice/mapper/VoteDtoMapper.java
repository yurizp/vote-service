package com.vote.voteservice.mapper;

import com.vote.voteservice.dto.VoteDto;

public class VoteDtoMapper {
  public static VoteDto create(String associatedId, boolean acceptMeetingAgenda) {
    return VoteDto.builder().approved(acceptMeetingAgenda).associatedId(associatedId).build();
  }
}
