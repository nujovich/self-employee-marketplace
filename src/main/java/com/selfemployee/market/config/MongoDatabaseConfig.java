package com.selfemployee.market.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.selfemployee.market.model.Bid;
import com.selfemployee.market.model.Project;
import com.selfemployee.market.model.Seller;
import com.selfemployee.market.mongo.codec.BidCodec;
import com.selfemployee.market.mongo.codec.ProjectCodec;
import com.selfemployee.market.mongo.codec.SellerCodec;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.selfemployee.market.repository")
public class MongoDatabaseConfig {

    @Bean
    public MongoClient mongo() {
        CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        Codec<Document> documentCodec = codecRegistry.get(Document.class);
        Codec<Project> projectCodec = new ProjectCodec(documentCodec);
        Codec<Bid> bidCodec = new BidCodec(documentCodec);
        Codec<Seller> sellerCodec = new SellerCodec(documentCodec);
        ConnectionString connectionString = new ConnectionString("mongodb+srv://nujovich:Cassandra.999@cluster0.xuefr.mongodb.net/Marketplace?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(codecRegistry,
                CodecRegistries.fromCodecs(documentCodec, projectCodec,bidCodec, sellerCodec));
        MongoClientSettings settings = MongoClientSettings.builder()
        .codecRegistry(pojoCodecRegistry)
        .applyConnectionString(connectionString)
        .build();
        return MongoClients.create(settings);
    }

    @Bean
	public MongoOperations mongoTemplate() {
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongo(), "Marketplace"));
		return mongoOps;
	}
    
}