package com.mongo_mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DownFileService {

    private final DownFileRepository repository;
    private final String downPath = "";

    @Transactional(readOnly = true)
    public void getList(Pageable of) throws Exception {
        List<DownFileCollection> arr = repository.findBySampleImageInfoArray(of);

        long selectCount = 0;
        long start = System.currentTimeMillis();

        for(int i = 0; i < arr.size(); i++) {
            DownFileCollection testCollection = arr.get(i);

            Object sampleImageInfoArray = testCollection.getSampleImageInfoArray();
            String id = testCollection.get_id();
            String s = String.valueOf(sampleImageInfoArray);
            System.out.println("탐색 객체 : " + i);

            try {
                String path = s.split(",")[1];
                String downURL= path.replace("path=", "");
                System.out.println("image Count : " + ++selectCount);
                try {
                    URL url = new URL(downURL);
                    ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                    FileOutputStream fos = new FileOutputStream(downPath + id + ".jpg");
                    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                    fos.close();;
                    System.out.println("success downloaded image");
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println(e);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("실행시간 : " + (end-start)/1000 + "초");
    }
}