package com.vote.voteservice.controller;

import com.vote.voteservice.controller.input.MeetingAgendaInput;
import com.vote.voteservice.controller.input.VoteInput;
import com.vote.voteservice.controller.response.MeetingAgendaResponse;
import com.vote.voteservice.controller.response.VoteResponse;
import com.vote.voteservice.mapper.MeetingAgendaDtoMapper;
import com.vote.voteservice.mapper.MeetingAgendaResponseMapper;
import com.vote.voteservice.mapper.VoteReponseMapper;
import com.vote.voteservice.service.MeetingAgendaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/meeting-agenda")
public class MeetingAgendaController {

  private MeetingAgendaService meetingAgendaService;

  @PostMapping("/create")
  public Mono<ResponseEntity> createMeetingAgenda(
      @RequestBody MeetingAgendaInput meetingAgendaInput) {
    return Mono.just(meetingAgendaInput)
        .map(MeetingAgendaDtoMapper::create)
        .flatMap(meetingAgendaService::createMeetingAgenda)
        .map(MeetingAgendaResponseMapper::create)
        .map(
            meetingAgendaResponse ->
                new ResponseEntity<>(meetingAgendaResponse, HttpStatus.CREATED));
  }

  @GetMapping("/{meetingId}")
  public Mono<ResponseEntity<MeetingAgendaResponse>> getMeetingAgendaById(
      @PathVariable("meetingId") String meetingId) {
    return meetingAgendaService
        .findById(meetingId)
        .map(MeetingAgendaResponseMapper::create)
        .map(
            meetingAgendaResponse ->
                new ResponseEntity<>(meetingAgendaResponse, HttpStatus.CREATED))
        .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
  }

  @PostMapping("/{meetingId}/start-vote")
  public Mono<ResponseEntity<MeetingAgendaResponse>> startVote(
      @PathVariable("meetingId") String meetingId) {
    return meetingAgendaService
        .startVote(meetingId)
        .map(MeetingAgendaResponseMapper::create)
        .map(agendaResponse -> new ResponseEntity<>(agendaResponse, HttpStatus.OK));
  }

  @PostMapping("/{meetingId}/vote")
  public Mono<ResponseEntity<VoteResponse>> vote(
          @PathVariable("meetingId") String meetingId, @RequestBody VoteInput voteInput) {
    return meetingAgendaService
            .addVote(meetingId, voteInput.getAssociatedId(), voteInput.isAcceptMeetingAgenda())
            .map(VoteReponseMapper::create)
            .map(agendaResponse -> new ResponseEntity<>(agendaResponse, HttpStatus.OK));
  }
}
