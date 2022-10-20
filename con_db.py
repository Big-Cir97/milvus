#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
@author: daewon
"""

from pymongo import MongoClient
socket.setdefaulttimeout(3)


# MONGO DB CONNECT
mongodb_URI = "connection info"
client = MongoClient(mongodb_URI)
db = client.patent
db.list_collection_names()

# 콜렉션 접속
db_connection = db.get_collection("collection_name")
get_collection = db.collection_name

# { "_id" : 0}
find_data = get_collection.find({"field" : {"$exists": True}})

# get image path
image_path = get_collection.find({qeury})
