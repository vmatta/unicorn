package com.self.unicorn.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.self.unicorn.domain.Color;
import com.self.unicorn.domain.EyeColor;
import com.self.unicorn.domain.HairColor;
import com.self.unicorn.domain.HornColor;
import com.self.unicorn.domain.IdentifiableMark;
import com.self.unicorn.domain.Side;
import com.self.unicorn.domain.Unit;
import com.self.unicorn.reposiotry.UnicornRepository;
import com.self.unicorn.UnicornApplication;
import com.self.unicorn.domain.CreateResponse;
import com.self.unicorn.domain.Unicorn;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = UnicornApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UnicornControllerITest {

  @LocalServerPort
  private int port;

  private String baseUri;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UnicornRepository unicornRepository;

  @BeforeEach
  void setUp() {
    baseUri = "http://localhost:" + port + "/unicorns";
    unicornRepository.deleteAll();
  }

  @Test
  void createShouldValidateEntity() {
    Unicorn unicorn = Unicorn.builder().build();
    ResponseEntity<String> stringResponseEntity = this.restTemplate.postForEntity(baseUri, unicorn, String.class);
    assertEquals(400, stringResponseEntity.getStatusCodeValue());
    assertTrue(stringResponseEntity.getBody().contains("\"error\":\"Bad Request\""));
  }

  @Test
  void createShouldReturnSuccess() {
    Unicorn unicorn = Unicorn.builder().name("Sparkle Princess")
        .hairColor(HairColor.WHITE)
        .hornLength(104)
        .hornColor(HornColor.GOLD)
        .eyeColor(EyeColor.SAPPHIRE)
        .height(94)
        .heightUnit(Unit.CM)
        .weight(104)
        .weightUnit(Unit.KG)
        .identifiableMarks(Arrays.asList(
            IdentifiableMark.builder().mark("star").color(Color.BLUE).location("near neck").side(
                Side.LEFT).build(),
            IdentifiableMark.builder().mark("circle").color(Color.RED).location("below neck").side(Side.RIGHT).build()
        ))
        .build();
    ResponseEntity<CreateResponse> stringResponseEntity = this.restTemplate.postForEntity(baseUri, unicorn, CreateResponse.class);
    assertEquals(201, stringResponseEntity.getStatusCodeValue());
  }

  @Test
  void retrieve() {
    Unicorn unicorn = Unicorn.builder().name("Sparkle Princess")
        .hairColor(HairColor.WHITE)
        .hornLength(104)
        .hornColor(HornColor.GOLD)
        .eyeColor(EyeColor.SAPPHIRE)
        .height(94)
        .heightUnit(Unit.CM)
        .weight(104)
        .weightUnit(Unit.KG)
        .identifiableMarks(Arrays.asList(
            IdentifiableMark.builder().mark("star").color(Color.BLUE).location("near neck").side(Side.LEFT).build(),
            IdentifiableMark.builder().mark("circle").color(Color.RED).location("below neck").side(Side.RIGHT).build()
        ))
        .build();
    Unicorn insert = unicornRepository.insert(unicorn);

    ResponseEntity<Unicorn> responseEntity = this.restTemplate.getForEntity(baseUri + "/" + insert.getUnicornId(), Unicorn.class);
    Unicorn entityBody = responseEntity.getBody();
    assertEquals(200, responseEntity.getStatusCodeValue());
    assertEquals(HairColor.WHITE, entityBody.getHairColor());
    assertEquals(104, entityBody.getHornLength());
    assertEquals(HornColor.GOLD, entityBody.getHornColor());
  }

  @Test
  void retrieveAllShouldReturnAllTheEntities() {
    insertMultipleUnicorn();
    ResponseEntity<List<Unicorn>> responseEntity = restTemplate.exchange(baseUri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Unicorn>>() {});
    assertEquals(200, responseEntity.getStatusCodeValue());
    List<Unicorn> body = responseEntity.getBody();
    List<String> stringList = Arrays.asList("Sparkle Princess1", "Sparkle Princess2", "Sparkle Princess3");
    assertEquals(3, body.stream().filter(unicorn -> stringList.contains(unicorn.getName())).count());
  }

  private void insertMultipleUnicorn() {
    Unicorn unicorn = Unicorn.builder().name("Sparkle Princess1")
        .hairColor(HairColor.WHITE)
        .hornLength(104)
        .hornColor(HornColor.GOLD)
        .eyeColor(EyeColor.SAPPHIRE)
        .height(94)
        .heightUnit(Unit.CM)
        .weight(104)
        .weightUnit(Unit.KG)
        .identifiableMarks(Arrays.asList(
            IdentifiableMark.builder().mark("star").color(Color.BLUE).location("near neck").side(Side.LEFT).build(),
            IdentifiableMark.builder().mark("circle").color(Color.RED).location("below neck").side(Side.RIGHT).build()
        ))
        .build();
    Unicorn unicorn2 = Unicorn.builder().name("Sparkle Princess2")
        .hairColor(HairColor.WHITE)
        .hornLength(104)
        .hornColor(HornColor.GOLD)
        .eyeColor(EyeColor.SAPPHIRE)
        .height(94)
        .heightUnit(Unit.CM)
        .weight(104)
        .weightUnit(Unit.KG)
        .identifiableMarks(Arrays.asList(
            IdentifiableMark.builder().mark("star").color(Color.BLUE).location("near neck").side(Side.LEFT).build(),
            IdentifiableMark.builder().mark("circle").color(Color.RED).location("below neck").side(Side.RIGHT).build()
        ))
        .build();
    Unicorn unicorn3 = Unicorn.builder().name("Sparkle Princess3")
        .hairColor(HairColor.WHITE)
        .hornLength(104)
        .hornColor(HornColor.GOLD)
        .eyeColor(EyeColor.SAPPHIRE)
        .height(94)
        .heightUnit(Unit.CM)
        .weight(104)
        .weightUnit(Unit.KG)
        .identifiableMarks(Arrays.asList(
            IdentifiableMark.builder().mark("star").color(Color.BLUE).location("near neck").side(Side.LEFT).build(),
            IdentifiableMark.builder().mark("circle").color(Color.RED).location("below neck").side(Side.RIGHT).build()
        ))
        .build();
    unicornRepository.insert(Arrays.asList(unicorn, unicorn2, unicorn3));
  }

  @Test
  void updateShouldReturnSuccess() {
    Unicorn unicorn = Unicorn.builder().name("Sparkle Princess1")
        .hairColor(HairColor.WHITE)
        .hornLength(104)
        .hornColor(HornColor.GOLD)
        .eyeColor(EyeColor.SAPPHIRE)
        .height(94)
        .heightUnit(Unit.CM)
        .weight(104)
        .weightUnit(Unit.KG)
        .identifiableMarks(Arrays.asList(
            IdentifiableMark.builder().mark("star").color(Color.BLUE).location("near neck").side(Side.LEFT).build(),
            IdentifiableMark.builder().mark("circle").color(Color.RED).location("below neck").side(Side.RIGHT).build()
        ))
        .build();
    Unicorn savedOne = unicornRepository.insert(unicorn);
    Unicorn updatedUnicorn = Unicorn.builder()
        .unicornId(savedOne.getUnicornId())
        .name("Sparkle Princess1")
        .hairColor(HairColor.GRAY)
        .hornLength(104)
        .hornColor(HornColor.GOLD)
        .eyeColor(EyeColor.BLACK)
        .height(94)
        .heightUnit(Unit.CM)
        .weight(104)
        .weightUnit(Unit.KG)
        .identifiableMarks(Arrays.asList(
            IdentifiableMark.builder().mark("star").color(Color.BLUE).location("near neck").side(Side.LEFT).build(),
            IdentifiableMark.builder().mark("circle").color(Color.RED).location("below neck").side(Side.RIGHT).build()
        ))
        .build();

    ResponseEntity<Unicorn> updatedEntity = restTemplate.exchange(baseUri + "/" + updatedUnicorn.getUnicornId(), HttpMethod.PUT, new HttpEntity<>(updatedUnicorn), new ParameterizedTypeReference<Unicorn>() {});
    assertEquals(200, updatedEntity.getStatusCodeValue());
    assertEquals(HairColor.GRAY, updatedEntity.getBody().getHairColor());
    assertEquals(EyeColor.BLACK, updatedEntity.getBody().getEyeColor());
  }
}