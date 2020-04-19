package com.vote.voteservice.mapper;

import com.vote.voteservice.controller.response.MeetingAgendaResponse;
import com.vote.voteservice.controller.response.VoteResponse;
import com.vote.voteservice.dto.MeetingAgendaDto;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MeetingAgendaResponseMapper {
  public static MeetingAgendaResponse create(MeetingAgendaDto meetingAgendaDto) {
    return MeetingAgendaResponse.builder()
        .description(meetingAgendaDto.getDescription())
        .title(meetingAgendaDto.getTitle())
        .id(meetingAgendaDto.getId())
        .createdDateTime(meetingAgendaDto.getCreatedDateTime())
        .durantionSecondsSession(meetingAgendaDto.getDurantionSecondsSession())
        .endVoteSessionDateTime(meetingAgendaDto.getEndVoteSessionDateTime())
        .votes(getVotes(meetingAgendaDto))
        .build();
  }

  private static List<VoteResponse> getVotes(MeetingAgendaDto meetingAgendaDto) {
    if (CollectionUtils.isEmpty(meetingAgendaDto.getVoteDtoList())) {
      return null;
    }
    return meetingAgendaDto.getVoteDtoList().stream()
        .map(VoteReponseMapper::create)
        .collect(Collectors.toList());
  }
}
