package com.mongo_webflux;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "kts_biblio")
public class DownFileCollection {

    @Id
    private String _id;

    // 칼럼
    @Field("sampleImageInfoArray")
    private Object sampleImageInfoArray;
}
