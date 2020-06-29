package de.neuefische.projectplanning.controller;

<<<<<<< HEAD
import de.neuefische.projectplanning.db.IdeaDb;
=======
>>>>>>> d612338ba8db4979eea4d6becb3a4d4b003bde14
import de.neuefische.projectplanning.db.IdeaMongoDb;
import de.neuefische.projectplanning.model.AddIdeaDto;
import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.utils.IdUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IdeaControllerTest {

  @LocalServerPort
  public int port;

  @Autowired
  public TestRestTemplate restTemplate;

  @Autowired
  private IdeaMongoDb db;

  @MockBean
  private IdUtils idUtils;

  @BeforeEach
  public void resetDatabase() {
    db.deleteAll();
  }

  @Test
  public void getIdeasShouldReturnAllIdeas() {
    //GIVEN
    String url = "http://localhost:" + port + "/api/ideas";
    db.save(new Idea("1", "Some Fancy Idea"));
    db.save(new Idea("2", "Some other Fancy Idea"));
    //WHEN
    ResponseEntity<Idea[]> response = restTemplate.getForEntity(url, Idea[].class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    Idea[] ideas = response.getBody();
    assertEquals(ideas.length, 2);
    assertEquals(ideas[0], new Idea("1", "Some Fancy Idea"));
    assertEquals(ideas[1], new Idea("2", "Some other Fancy Idea"));
  }

  @Test
  public void addIdeaShouldAddIdea() {
    // GIVEN
    when(idUtils.generateRandomId()).thenReturn("some-random-id");

    AddIdeaDto addIdeaDto = new AddIdeaDto( "some description");
    String url = "http://localhost:" + port + "/api/ideas";

    HttpEntity<AddIdeaDto> requestEntity = new HttpEntity<>(addIdeaDto);

    // WHEN
    ResponseEntity<Idea> putResponse = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Idea.class);

    // THEN
    Idea expectedIdea = new Idea("some-random-id", "some description");
    assertEquals(HttpStatus.OK, putResponse.getStatusCode());
    assertNotNull(putResponse.getBody());
    assertEquals(expectedIdea, putResponse.getBody());

    Optional<Idea> byId = db.findById("some-random-id");
    assertTrue(byId.isPresent());
    assertEquals(byId.get(),expectedIdea);
  }

  @Test
  @DisplayName("add idea should return badRequest when description is shorter than 5")
  public void checkMinLengthDescription(){
    //GIVEN
    AddIdeaDto addIdeaDto = new AddIdeaDto( "some");
    String url = "http://localhost:" + port + "/api/ideas";

    HttpEntity<AddIdeaDto> requestEntity = new HttpEntity<>(addIdeaDto);

    //WHEN
    ResponseEntity<Idea> putResponse = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Idea.class);


    //THEN
    assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
  }

  @Test
  @DisplayName("delete by id should delete idea with id")
  public void deleteIdea(){
    //GIVEN
    db.save(new Idea("1", "Some Fancy Idea"));
    db.save(new Idea("2", "Some other Fancy Idea"));

    //WHEN
    String url = "http://localhost:" + port + "/api/ideas/2";
    restTemplate.delete(url);

    //THEN
    assertTrue(db.findById("2").isEmpty());
  }


  @Test
  @DisplayName("get by id should return idea with id")
  public void getIdeaById(){
    //GIVEN
    db.save(new Idea("1", "Some Fancy Idea"));
    db.save(new Idea("2", "Some other Fancy Idea"));

    //WHEN
    String url = "http://localhost:" + port + "/api/ideas/2";
    ResponseEntity<Idea> response = restTemplate.getForEntity(url, Idea.class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody(), new Idea("2", "Some other Fancy Idea"));
  }

  @Test
  @DisplayName("when id not exists get idea by id should return status not found")
  public void getIdeaByIdNotfound(){
    //GIVEN
    db.save(new Idea("1", "Some Fancy Idea"));
    //WHEN
    String url = "http://localhost:" + port + "/api/ideas/2";
    ResponseEntity<Idea> response = restTemplate.getForEntity(url, Idea.class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
  }
}
