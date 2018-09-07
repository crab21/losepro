package com.example.demo.Controller;

import com.example.demo.Entity.Leader;
import com.example.demo.Entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Created by k on 2018/8/13.
 */
@RestController
public class MongodbController {
    @Autowired
    MongoTemplate mongoTemplate;


    @RequestMapping("/t1")
    public String name() {
        List<User> wang = mongoTemplate.find(new Query(Criteria.where("sex").is("male")), User.class);
        System.out.println(wang.size());
        return "ok";
    }

    @RequestMapping("/findLeader")
    public String findLeader() {
        List<Leader> objects = mongoTemplate.find(new Query(Criteria.where("partMent").is("ss")), Leader.class, "le");
        System.out.println(objects.size());
        System.out.println(objects.get(0).getId());

        return "ok";
    }

    @RequestMapping("/findMaxLeader")
    public String findMaxLeader() {
        Leader id = mongoTemplate.findOne(new BasicQuery("{}").with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id"))).limit(1), Leader.class, "le");
        System.out.println(id.getNames());
        return "ok";
    }

    @RequestMapping("/distinctLeader")
    public String distinctLeader() {
//        Query query = Query.query(Criteria.where("partMent").is("ss"));
//        List distinct = mongoTemplate.getCollection("le").distinct("names", query.getQueryObject());
//        DistinctIterable<TResult> distinct = mongoTemplate.getCollection("person").distinct("myFriends", query.getQueryObject());
        DBObject o1 = new BasicDBObject("_id", new BasicDBObject("$gt", 25));

        CodecProvider build = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(build));

        BasicDBObject query = new BasicDBObject();
        query.put("partMent", new BasicDBObject("$eq", "ss"));
        MongoCollection<Document> collection = mongoTemplate.getDb().withCodecRegistry(codecRegistry).getCollection("le");
        DistinctIterable<Leader> partMent = collection.distinct("partMent", Leader.class);

        ArrayList<Leader> into = partMent.into(new ArrayList<>());

        System.out.println(into.size());

        return "ok";
    }

    @RequestMapping("/distinctUser")
    public String distinctUser() {
//        Query query = Query.query(Criteria.where("partMent").is("ss"));
//        List distinct = mongoTemplate.getCollection("le").distinct("names", query.getQueryObject());
//        DistinctIterable<TResult> distinct = mongoTemplate.getCollection("person").distinct("myFriends", query.getQueryObject());
//        DBObject o1 = new BasicDBObject("_id", new BasicDBObject("$gt", 25));
        CodecProvider build = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(build));
        ArrayList<User> age = mongoTemplate.getDb().withCodecRegistry(codecRegistry).getCollection("test_mongodb.users", User.class).distinct("age", User.class).into(new ArrayList<>());


        System.out.println(age.size());

        return "ok";
    }

    @RequestMapping("/addLeader")
    public String hi() {

        List<Leader> list = new ArrayList<>();
        for (int i = 20; i < 30; i++) {
            list.add(new Leader(i + "w", "ss"));
        }
        mongoTemplate.insert(list, "le");
        return "ok";
    }

    @RequestMapping("/distinctLea")
    public String distinctLea() {
        MongoClient client = new MongoClient();
        CodecProvider build = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(build));


        MongoCollection<Document> collection = client.getDatabase("test_mongodb").withCodecRegistry(codecRegistry).getCollection("le");
        DistinctIterable<String> partMent = collection.distinct("partMent", String.class);
        ArrayList<String> into = partMent.into(new ArrayList<>());
        System.out.println(into.size());
        for (String result :
                into) {
            System.out.println(result);
        }

        return "ok";
    }
}


