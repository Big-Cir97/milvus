package com.mongo_mvc;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface DownFileRepository extends MongoRepository<DownFileCollection, String> {
    @Query("{'sampleImageInfoArray' : {$exists:true}}")
    // @Query("{$where : 'this.sampleImageInfoArray.length !== 0'}")
    List<DownFileCollection> findBySampleImageInfoArray(Pageable pageable);

    @Query("{'sampleImageInfoArray' : {$exists:true}}")
    Stream<DownFileCollection> streamAll(Pageable pageable);

    @Query("{'sampleImageInfoArray' : true}")
    Stream<DownFileCollection> streamBySampleImageInfoArray(Pageable pageable);
}
