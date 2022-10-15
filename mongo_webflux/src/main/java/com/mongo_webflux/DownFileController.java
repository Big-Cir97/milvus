package com.mongo_webflux;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class DownFileController {

    private final DownFileService service;
    private final DownFileRepository repository;

    @GetMapping("/download")
    public Flux<DownFileCollection> getCollection(@RequestParam("page") int of) {
        Pageable page = PageRequest.of(of, 50);
        Flux<DownFileCollection> arr = repository.findBySampleImageInfoArray(page);
        int selectSize = arr.count().block().intValue();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < selectSize; i++) {
            service.getCollection(i, arr.elementAt(i));
        }
        stopWatch.stop();
        System.out.println("실행시간 : " + stopWatch.getTotalTimeSeconds() +  "초");
        return arr;
    }
}


