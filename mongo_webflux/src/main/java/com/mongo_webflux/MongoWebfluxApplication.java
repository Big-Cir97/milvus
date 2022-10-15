package com.mongo_webflux;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.servlet.function.RouterFunctions;
import reactor.core.publisher.Flux;

@EnableMongoAuditing
@EnableReactiveMongoRepositories
@SpringBootApplication
@RequiredArgsConstructor
public class MongoWebfluxApplication {

    private final DownFileService service;

    public static void main(String[] args) {
        SpringApplication.run(MongoWebfluxApplication.class, args);
    }

//    @Bean
//    public RouterFunction<ServerResponse> route() {
//        return RouterFunctions.route(
//                        RequestPredicates.GET("/download")
//                        .and(service::getCollection), DownFileCollection.class);
//    }

}
