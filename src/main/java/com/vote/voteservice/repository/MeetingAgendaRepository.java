package com.vote.voteservice.repository;

import com.vote.voteservice.enums.VoteStatus;
import com.vote.voteservice.model.MeetingAgendaModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MeetingAgendaRepository
    extends ReactiveCrudRepository<MeetingAgendaModel, String> {

  Mono<MeetingAgendaModel> findByIdAndVoteStatus(String Id, VoteStatus voteStatus);
}
