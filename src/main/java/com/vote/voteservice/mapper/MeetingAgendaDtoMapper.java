package com.vote.voteservice.mapper;

import com.vote.voteservice.controller.input.MeetingAgendaInput;
import com.vote.voteservice.dto.MeetingAgendaDto;
import com.vote.voteservice.dto.VoteDto;
import com.vote.voteservice.model.MeetingAgendaModel;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingAgendaDtoMapper {
    public static MeetingAgendaDto create(MeetingAgendaInput meetingAgendaInput) {
        return MeetingAgendaDto.builder()
                .description(meetingAgendaInput.getDescription())
                .title(meetingAgendaInput.getTitle())
                .durantionSecondsSession(meetingAgendaInput.getVoteDurationTimeInSeconds())
                .build();
    }

    public static MeetingAgendaDto create(MeetingAgendaModel meetingAgendaModel) {
        return MeetingAgendaDto.builder()
                .description(meetingAgendaModel.getDescription())
                .title(meetingAgendaModel.getTitle())
                .id(meetingAgendaModel.getId())
                .createdDateTime(LocalDateTime.ofInstant(meetingAgendaModel.getCreatedDate(), ZoneId.of("America/Sao_Paulo")))
                .durantionSecondsSession(meetingAgendaModel.getDurantionSecondsSession())
                .endVoteSessionDateTime(meetingAgendaModel.getEndVoteSession())
                .startVoteSession(meetingAgendaModel.getStartVoteSession())
                .voteDtoList(getVoteList(meetingAgendaModel))
                .build();
    }

    private static List<VoteDto> getVoteList(MeetingAgendaModel meetingAgendaModel) {
        if(CollectionUtils.isEmpty(meetingAgendaModel.getVoteModelList())){
            return null;
        }
        return meetingAgendaModel.getVoteModelList().stream()
                .map(voteModel -> VoteDtoMapper.create(voteModel.getAssociatedId(), voteModel.getApproved())).collect(Collectors.toList());
    }

}
