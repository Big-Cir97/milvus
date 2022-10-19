package com.mongo_webflux;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Service
@RequiredArgsConstructor
public class DownFileService {
    private final String downPath = "/home/master/Desktop/milvus_test/save_img";
    // "/Users/daewon/Desktop/mongt/save/";

    public void getCollection(int i, Mono<DownFileCollection> getObject) {
        Object sampleImageInfoArray = getObject.block().getSampleImageInfoArray();
        String id = getObject.block().get_id();

        String s = String.valueOf(sampleImageInfoArray);
        System.out.println("탐색 객체 : " + i);

        try {
            String path = s.split(",")[1];
            String downURL= path.replace("path=", "");
            try {
                URL url = new URL(downURL);
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(downPath + id + ".jpg");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();;
                System.out.println("success downloaded image");
            } catch (Exception e) {
                // e.printStackTrace();
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }
}
