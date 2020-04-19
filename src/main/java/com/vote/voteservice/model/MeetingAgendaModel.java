package com.vote.voteservice.model;

import com.vote.voteservice.enums.VoteStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@Document("pauta")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MeetingAgendaModel implements Persistable<String> {

  @Id private String id;

  private String title;

  private String description;

  private Integer durantionSecondsSession;

  private VoteStatus voteStatus;

  private LocalDateTime startVoteSession;

  private LocalDateTime endVoteSession;

  @LastModifiedDate private Instant lastModifiedDate;

  @CreatedDate private Instant createdDate;

  private Set<VoteModel> voteModelList;

  @Override
  public boolean isNew() {
    return Objects.isNull(id);
  }

  public MeetingAgendaModel addVote(VoteModel voteModel) {
    if (CollectionUtils.isEmpty(voteModelList)) {
      voteModelList = new HashSet<>();
    }
    voteModelList.add(voteModel);
    return this;
  }

  public MeetingAgendaModel startVoteSession() {
    if(Objects.nonNull(voteModelList)){
      return this;
    }
    voteStatus = VoteStatus.STARTED;
    endVoteSession = LocalDateTime.now().plusSeconds(durantionSecondsSession);
    startVoteSession = LocalDateTime.now();
    return this;
  }
}
