package com.self.unicorn.reposiotry;

import com.self.unicorn.domain.Unicorn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnicornRepository extends MongoRepository<Unicorn, String> {

}
