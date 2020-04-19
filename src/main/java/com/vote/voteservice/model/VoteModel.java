package com.vote.voteservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteModel {

  private Boolean approved;

  private String associatedId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VoteModel voteModel = (VoteModel) o;
    return associatedId.equals(voteModel.associatedId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(associatedId);
  }
}
