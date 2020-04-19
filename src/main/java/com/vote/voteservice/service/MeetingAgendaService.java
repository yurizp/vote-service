package com.vote.voteservice.service;

import com.vote.voteservice.dto.MeetingAgendaDto;
import com.vote.voteservice.dto.VoteDto;
import com.vote.voteservice.enums.VoteStatus;
import com.vote.voteservice.mapper.MeetingAgendaDtoMapper;
import com.vote.voteservice.mapper.MeetingAgendaModelMapper;
import com.vote.voteservice.mapper.VoteDtoMapper;
import com.vote.voteservice.model.VoteModel;
import com.vote.voteservice.repository.MeetingAgendaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class MeetingAgendaService {

  private MeetingAgendaRepository meetingAgendaRepository;

  public Mono<MeetingAgendaDto> createMeetingAgenda(MeetingAgendaDto meetingAgendaDto) {
    return Mono.just(meetingAgendaDto)
        .map(MeetingAgendaModelMapper::create)
        .flatMap(meetingAgendaRepository::save)
        .map(MeetingAgendaDtoMapper::create);
  }

  public Mono<MeetingAgendaDto> findById(String meetingId) {
    return meetingAgendaRepository.findById(meetingId).map(MeetingAgendaDtoMapper::create);
  }

  public Mono<MeetingAgendaDto> startVote(String meetingId) {
    return meetingAgendaRepository
        .findById(meetingId)
        .map(agendaModel -> agendaModel.startVoteSession())
        .flatMap(meetingAgendaRepository::save)
        .map(MeetingAgendaDtoMapper::create);
  }

  public Mono<VoteDto> addVote(String meetingId, String associatedId, boolean acceptMeetingAgenda) {
    return meetingAgendaRepository
        .findByIdAndVoteStatus(meetingId, VoteStatus.STARTED)
        .switchIfEmpty(
            Mono.error(
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found meeting to vote.")))
        .map(
            meetingAgendaModel ->
                meetingAgendaModel.addVote(
                    VoteModel.builder()
                        .approved(acceptMeetingAgenda)
                        .associatedId(associatedId)
                        .build()))
        .flatMap(meetingAgendaRepository::save)
        .map(meetingAgendaModel -> VoteDtoMapper.create(associatedId, acceptMeetingAgenda));
  }
}
