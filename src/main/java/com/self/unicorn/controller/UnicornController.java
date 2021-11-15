package com.self.unicorn.controller;

import com.self.unicorn.domain.CreateResponse;
import com.self.unicorn.service.UnicornService;
import com.self.unicorn.domain.Unicorn;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/unicorns")
public class UnicornController {

  private final UnicornService unicornService;

  @Autowired
  public UnicornController(UnicornService unicornService) {
    this.unicornService = unicornService;
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<CreateResponse> create(@RequestBody @Valid Unicorn unicorn) {
    Unicorn unicornCreated =  unicornService.create(unicorn);
    CreateResponse createResponse = new CreateResponse(unicornCreated.getUnicornId());
    return ResponseEntity.status(HttpStatus.CREATED).body(createResponse);
  }

  @GetMapping("/{unicornId}")
  public ResponseEntity<Unicorn> retrieve(@PathVariable(value = "unicornId") String unicornId) {
    Unicorn unicorn = unicornService.retrieve(unicornId);
    return ResponseEntity.ok(unicorn);
  }

  @GetMapping
  public ResponseEntity<List<Unicorn>> retrieve() {
    List<Unicorn> unicorns = unicornService.retrieveAll();
    return ResponseEntity.ok(unicorns);
  }

  @PutMapping("/{unicornId}")
  public ResponseEntity<?> update(@PathVariable(value = "unicornId") String unicornId, @RequestBody @Valid Unicorn unicorn) {
    Unicorn updatedUnicorn = unicornService.update(unicornId, unicorn);
    return ResponseEntity.ok(updatedUnicorn);
  }

}
