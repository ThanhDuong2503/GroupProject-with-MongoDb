package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.db.IdeaDb;
import de.neuefische.projectplanning.model.Idea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IdeaControllerTest {

  @LocalServerPort
  public int port;

  @Autowired
  public TestRestTemplate restTemplate;

  @Autowired
  private IdeaDb db;

  @BeforeEach
  public void resetDatabase(){
    db.clearDb();
  }

  @Test
  public void getIdeasShouldReturnAllIdeas() {
    //GIVEN
    String url = "http://localhost:" + port + "/api/ideas";
    db.add(new Idea("1","Some Fancy Idea"));
    db.add(new Idea("2","Some other Fancy Idea"));
    //WHEN
    ResponseEntity<Idea[]> response = restTemplate.getForEntity(url, Idea[].class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    Idea[] ideas = response.getBody();
    assertEquals(ideas.length, 2);
    assertEquals(ideas[0],new Idea("1","Some Fancy Idea"));
    assertEquals(ideas[1],new Idea("2","Some other Fancy Idea"));
  }

  @Test
  public void addIdeashouldAddIdea(){
    // GIVEN
    String description = "some description";
    String url = "http://localhost:" + port + "/api/ideas";

    HttpEntity<String> requestEntity = new HttpEntity<>(description);
    // WHEN
    ResponseEntity<Idea> putResponse = restTemplate.exchange(url, HttpMethod.PUT,requestEntity, Idea.class);
    // THEN

    assertEquals(HttpStatus.OK,putResponse.getStatusCode());
    assertNotNull(putResponse.getBody());
    assertEquals(description,putResponse.getBody().getDescription());
    assertNotNull(putResponse.getBody().getId());
  }
}
