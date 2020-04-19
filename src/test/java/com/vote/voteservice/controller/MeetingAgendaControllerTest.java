package com.vote.voteservice.controller;

import com.vote.voteservice.dto.MeetingAgendaDto;
import com.vote.voteservice.service.MeetingAgendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MeetingAgendaController.class)
@Import(MeetingAgendaService.class)
public class MeetingAgendaControllerTest {

  @MockBean private MeetingAgendaService meetingAgendaService;

  @Autowired private WebTestClient webClient;

  @Test
  void shouldReturnMeetingAgendaById() throws IOException {
    when(meetingAgendaService.findById(any()))
        .thenReturn(
            Mono.just(
                MeetingAgendaDto.builder()
                    .id("id")
                    .description("description")
                    .title("title")
                    .durantionSecondsSession(60)
                    .createdDateTime(LocalDateTime.of(2020, 12, 20, 20, 20))
                    .build()));

    String requestBody = getFile("src/test/resources/json/inputBodyNewMeetingAgenda.json.json");
    String responseBody = getFile("src/test/resources/json/responseBodyNewMeetingAgenda.json.json");

    webClient
        .post()
        .uri("/meeting-agenda/create")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(requestBody)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody()
        .json(responseBody);
  }

  private String getFile(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(path)));
  }
}
