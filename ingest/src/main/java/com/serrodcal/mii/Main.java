package com.serrodcal.mii;

import java.util.List;
import java.util.concurrent.CompletionStage;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.serrodcal.mii.dto.TweetDTO;

import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import akka.NotUsed;
import akka.actor.ActorSystem;

import akka.stream.*;
import akka.stream.alpakka.mongodb.javadsl.MongoSource;
import akka.stream.javadsl.*;

public class Main {

    private static final String DATASTORE = "ssiilab1";
	private static final String URI = "mongodb://alumno:alumno1@ssiilab1-shard-00-00.o2eqo.mongodb.net:27017,ssiilab1-shard-00-01.o2eqo.mongodb.net:27017,ssiilab1-shard-00-02.o2eqo.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-q2liyc-shard-0&authSource=admin&retryWrites=true&w=majority";

    public static void main(String[] args) throws InterruptedException {
        final ActorSystem system = ActorSystem.create();
        
        PojoCodecProvider codecProvider = PojoCodecProvider.builder().register(TweetDTO.class).build();
        CodecRegistry codecRegistry = CodecRegistries.fromProviders(codecProvider, new ValueCodecProvider());

        MongoClient client = MongoClients.create("mongodb://alumno:alumno1@ssiilab1-shard-00-00.o2eqo.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-q2liyc-shard-0&authSource=admin&retryWrites=true&w=majority");
        //MongoClient client = MongoClients.create(URI);
        MongoDatabase db = client.getDatabase(DATASTORE);
        MongoCollection<TweetDTO> tweetsCollection = db.getCollection("tweets", TweetDTO.class).withCodecRegistry(codecRegistry);

        final Source<TweetDTO, NotUsed> source = MongoSource.create(tweetsCollection.find(TweetDTO.class));
        source.runForeach(i -> System.out.println(i), system);

        //final CompletionStage<List<TweetDTO>> rows = source.alsoTo(null).runWith(Sink.seq(), system);

        // final Source<Integer, NotUsed> source = Source.range(1, 100);
        // source.runForeach(i -> System.out.println(i), system);
        // Flow[Int].alsoTo(Sink.foreach(println(_))).to(Sink.ignore)
        //https://doc.akka.io/docs/akka/current/stream/operators/Source-or-Flow/sliding.html
        //https://softwaremill.com/windowing-data-in-akka-streams/
    }

}
