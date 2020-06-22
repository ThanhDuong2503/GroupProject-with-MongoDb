package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.model.Idea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IdeaControllerTest {

  @LocalServerPort
  public int port;

  @Autowired
  public TestRestTemplate restTemplate;

  @Test
  public void getIdeasShouldReturnAllIdeas() {
    //GIVEN
    String url = "http://localhost:" + port + "/api/ideas";

    //WHEN
    ResponseEntity<Idea[]> response = restTemplate.getForEntity(url, Idea[].class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    Idea[] ideas = response.getBody();
    assertEquals(ideas.length, 2);
    assertEquals(ideas[0],new Idea("1","Some Fancy Idea"));
    assertEquals(ideas[1],new Idea("2","Some other Fancy Idea"));
  }
}
