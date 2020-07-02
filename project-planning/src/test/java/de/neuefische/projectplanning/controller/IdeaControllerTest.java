package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.db.IdeaMongoDb;
import de.neuefische.projectplanning.db.UserDb;
import de.neuefische.projectplanning.model.AddIdeaDto;
import de.neuefische.projectplanning.model.Idea;
import de.neuefische.projectplanning.model.LoginData;
import de.neuefische.projectplanning.model.PlanningUser;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

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

  @Autowired
  public PasswordEncoder encoder;

  @Autowired
  public UserDb userDb;

  @MockBean
  private IdUtils idUtils;

  @BeforeEach
  public void resetDatabase() {
    db.deleteAll();
    userDb.deleteAll();
  }

  private String loginUser() {
    String savePassword = "savePassword";
    PlanningUser user = new PlanningUser("superUser", encoder.encode(savePassword), "admin");
    userDb.save(user);

    String loginUrl = "http://localhost:" + port + "/auth/login";
    ResponseEntity<String> tokenResponse = restTemplate.postForEntity(loginUrl, new LoginData("superUser", "savePassword"), String.class);
    return tokenResponse.getBody();
  }

  @Test
  public void getIdeasShouldReturnAllIdeas() {
    //GIVEN
    String token = loginUser();

    String url = "http://localhost:" + port + "/api/ideas";
    db.save(new Idea("1", "Some Fancy Idea", "user1"));
    db.save(new Idea("2", "Some other Fancy Idea", "superUser"));


    //WHEN
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity entity = new HttpEntity(headers);
    ResponseEntity<Idea[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Idea[].class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    Idea[] ideas = response.getBody();
    assertEquals(ideas.length, 2);
    assertEquals(ideas[0], new Idea("1", "Some Fancy Idea", "user1"));
    assertEquals(ideas[1], new Idea("2", "Some other Fancy Idea", "superUser"));
  }


  @Test
  public void addIdeaShouldAddIdea() {
    // GIVEN
    String token = loginUser();

    when(idUtils.generateRandomId()).thenReturn("some-random-id");

    AddIdeaDto addIdeaDto = new AddIdeaDto("some description");
    String url = "http://localhost:" + port + "/api/ideas";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<AddIdeaDto> requestEntity = new HttpEntity<>(addIdeaDto, headers);

    // WHEN
    ResponseEntity<Idea> putResponse = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Idea.class);

    // THEN
    Idea expectedIdea = new Idea("some-random-id", "some description", "superUser");
    assertEquals(HttpStatus.OK, putResponse.getStatusCode());
    assertNotNull(putResponse.getBody());
    assertEquals(expectedIdea, putResponse.getBody());

    Optional<Idea> byId = db.findById("some-random-id");
    assertTrue(byId.isPresent());
    assertEquals(byId.get(), expectedIdea);
  }

  @Test
  @DisplayName("add idea should return badRequest when description is shorter than 5")
  public void checkMinLengthDescription(){
    //GIVEN
    String token = loginUser();
    AddIdeaDto addIdeaDto = new AddIdeaDto( "some");
    String url = "http://localhost:" + port + "/api/ideas";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<AddIdeaDto> requestEntity = new HttpEntity<>(addIdeaDto,headers);

    //WHEN
    ResponseEntity<Idea> putResponse = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Idea.class);


    //THEN
    assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
  }

  @Test
  @DisplayName("delete by id should delete idea with id")
  public void deleteIdea(){
    //GIVEN
    String token = loginUser();
    db.save(new Idea("1", "Some Fancy Idea", "user1"));
    db.save(new Idea("2", "Some other Fancy Idea", "user1"));

    //WHEN
    String url = "http://localhost:" + port + "/api/ideas/2";
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity entity = new HttpEntity(headers);
    restTemplate.exchange(url,HttpMethod.DELETE,entity,Void.class);

    //THEN
    assertTrue(db.findById("2").isEmpty());
  }


  @Test
  @DisplayName("get by id should return idea with id")
  public void getIdeaById(){
    //GIVEN
    String token = loginUser();
    db.save(new Idea("1", "Some Fancy Idea", "user1"));
    db.save(new Idea("2", "Some other Fancy Idea", "user2"));

    //WHEN
    String url = "http://localhost:" + port + "/api/ideas/2";
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity entity = new HttpEntity(headers);
    ResponseEntity<Idea> response = restTemplate.exchange(url, HttpMethod.GET, entity, Idea.class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody(), new Idea("2", "Some other Fancy Idea", "user2"));
  }

  @Test
  @DisplayName("when id not exists get idea by id should return status not found")
  public void getIdeaByIdNotfound(){
    //GIVEN
    String token = loginUser();
    db.save(new Idea("1", "Some Fancy Idea", "user1"));
    //WHEN
    String url = "http://localhost:" + port + "/api/ideas/2";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity entity = new HttpEntity(headers);
    ResponseEntity<Idea> response = restTemplate.exchange(url, HttpMethod.GET, entity, Idea.class);

    //THEN
    assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
  }
}
