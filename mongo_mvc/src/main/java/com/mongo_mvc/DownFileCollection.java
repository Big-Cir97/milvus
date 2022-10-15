package com.mongo_mvc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "")
public class DownFileCollection {
    @Id
    private String _id;

    @Field("sampleImageInfoArray")
    private Object sampleImageInfoArray;
}
