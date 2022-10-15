package com.mongo_mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DownFileController {

    private final DownFileService service;

    @GetMapping("/download")
    public void getList(@RequestParam("page") int page) throws Exception {
        Pageable of = PageRequest.of(page, 10);
        service.getList(of);
    }
}
