package com.self.unicorn.service;

import com.self.unicorn.exception.UnicornNotFoundException;
import com.self.unicorn.exception.UnicornValidationException;
import com.self.unicorn.reposiotry.UnicornRepository;
import com.self.unicorn.domain.Unicorn;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UnicornService {

  private final UnicornRepository unicornRepository;

  @Autowired
  public UnicornService(UnicornRepository unicornRepository) {
    this.unicornRepository = unicornRepository;
  }

  public Unicorn create(Unicorn unicorn) {
    if (unicorn.getUnicornId() != null) {
      throw new UnicornValidationException("create should not contain unicorn id");
    }
    Unicorn savedUnicorn = unicornRepository.insert(unicorn);
    log.debug("unicorn saved successfully with id {}", savedUnicorn.getUnicornId());
    return savedUnicorn;
  }

  public Unicorn retrieve(String unicornId) {
    log.debug("Request received for retrieving unicorn id {}", unicornId);
    return unicornRepository.findById(unicornId).orElseThrow(() -> new UnicornNotFoundException("does not exist"));
  }

  public Unicorn update(String unicornId, Unicorn unicorn) {
    Unicorn savedUnicorn = this.retrieve(unicornId);
    Unicorn updatedUnicorn = unicornRepository.save(unicorn);
    log.debug("unicorn updated successfully with id {}", updatedUnicorn.getUnicornId());
    return updatedUnicorn;
  }

  public List<Unicorn> retrieveAll() {
    return unicornRepository.findAll();
  }
}
