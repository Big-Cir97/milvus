package com.mongo_webflux;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DownFileRepository extends ReactiveCrudRepository<DownFileCollection, String> {

    Flux<DownFileCollection> findAll();

    // @Query("{'sampleImageInfoArray' : {$exists:true}}")
    @Query("{$where : 'this.sampleImageInfoArray.length !== 0'}")
    Flux<DownFileCollection> findBySampleImageInfoArray(Pageable pageable);
}
