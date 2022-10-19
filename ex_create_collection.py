#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
@author: daewon
"""

from pymilvus import connections, utility, Collection, CollectionSchema, FieldSchema, DataType
import os, glob, random
import numpy as np

img_path = sorted(glob.glob("./mongt/save/*.jpg"))

file_box = []
for i in img_path :
    filename = i.split("/")[3]
    file_box.append(filename)
    
# milvus connection
connections.connect()

# select exist collections()
utility.list_collections()

# define field in collection
# p_id = FieldSchema(name, dtype, kwargs)
p_id = FieldSchema("id",
                   dtype = DataType.INT64, 
                   is_primary = True
                   )

field1 = FieldSchema("test_col", 
                     dtype = DataType.VARCHAR, 
                     max_length = 200,
                     )

field2 = FieldSchema("test_col2",
                     dtype = DataType.FLOAT_VECTOR, 
                     dim = 2
)


test_schema = CollectionSchema(fields = [p_id, field1, field2])

# define collection name
collection_name = "test5"

# create collection
collection = Collection(name = collection_name,
                        schema = test_schema,
                        using = 'default',
                        shards_num = 3)

# load collection
collection = Collection(collection_name)

# check exist collections
utility.list_collections()

# define data, insert into collection 
data = [ [i for i in range(161)],
         [str(i) for i in file_box],
         [[random.random() for _ in range(2)] for _ in range(161)], #[i for i in ar],
         ]

arr2 = [[random.random() for _ in range(2)] for _ in range(161)]
np.ndim(arr2)

# 컬렉션에 data 삽입
collection.insert(data)

# 현재 collection load
collection = Collection(collection_name)
collection.load()

# Query 조회 -> parameter expr: boolean
# boolean expr rules -> documents 참고
res = collection.query(
    expr = "id < %d" % len(file_box),
    output_fields = ["id", "test_col", "test_col2"]
    )

# default id로 오름차순 정렬
sorted_res = sorted(res, key=lambda k: k["id"])

# select한 data출력
sorted_res

d_str = ["mil", "vs", "test"]
data2 = [ [i for i in range(3)],
          [str(i) for i in d_str],
          [[random.random() for _ in range(2)] for _ in range(2)]
          ]
