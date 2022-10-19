# milvus

# Schema Define, Collection


>> from config import MILVUS_HOST, MILVUS_PORT, VECTOR_DIMENSION, METRIC_TYPE

# # Field Schema define
>> from pymilvus import CollectionSchema, FieldSchema, DataType

>> book_id = FieldSchema(				
  name="book_id", 
  dtype=DataType.INT64, 
  is_primary=True, 
  auto_id=True
)

>> book_intro = FieldSchema(
  name="book_intro", 
  dtype=DataType.FLOAT_VECTOR, 
  dim=2
)

# # Collection Schema define(필드 스키마 주입)
schema = CollectionSchema(
  fields=[book_id, book_name, word_count, book_intro], 
  description="Test book search"
)

collection_name = "book"

# # Create Collection 
>> from pymilvus import Collection

>> collection = Collection(
    name=collection_name, 
    schema=schema, 
    using='default', 
    shards_num=2,
    )

#  # Check collection exists
>> from pymilvus import utility

>> utility.list_collections()			# select all collection
>> utility.has_collection("book")

>> utility.drop_collection(“book”)	# drop collection

# Drop collection
>> utility.drop_collection("book")


# Partition

# Create Partition
>> from pymilvus import Collection

>> collection.create_partition(“novel”)		// default partition존재, 그뒤에 “name”: “novel” 파티션 추가

# Drop Partition
>> collection.drop_partition("novel") 	


# Check collection details
collection.schema                # Return the schema.CollectionSchema of the collection.

collection.description           # Return the description of the collection.

collection.name                  # Return the name of the collection.

collection.is_empty              # Return the boolean value that indicates if the collection is empty.

collection.num_entities          # Return the number of entities in the collection.

collection.primary_field         # Return the schema.FieldSchema of the primary key field.

collection.partitions            # Return the list[Partition] object.

collection.indexes               # Return the list[Index] object.

# use
- spring : mongodb에서 img 다운로드
- milvus : 다운받은 img, 거리벡터 변환 후 저장 -> 업로드 된 사진과 유사성 검사(distance로 비교)
