package com.vote.voteservice.mapper;

import com.vote.voteservice.dto.MeetingAgendaDto;
import com.vote.voteservice.model.MeetingAgendaModel;

public class MeetingAgendaModelMapper {
  public static MeetingAgendaModel create(MeetingAgendaDto meetingAgendaDto) {
    return MeetingAgendaModel.builder()
        .description(meetingAgendaDto.getDescription())
        .title(meetingAgendaDto.getTitle())
        .id(meetingAgendaDto.getId())
        .durantionSecondsSession(meetingAgendaDto.getDurantionSecondsSession())
        .build();
  }
}
