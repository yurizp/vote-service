package com.vote.voteservice.mapper;

import com.vote.voteservice.controller.response.VoteResponse;
import com.vote.voteservice.dto.VoteDto;

public class VoteReponseMapper {
    public static VoteResponse create(VoteDto voteDto) {
        return VoteResponse.builder()
                .approved(voteDto.getApproved())
                .associatedId(voteDto.getAssociatedId())
                .build();
    }
}
