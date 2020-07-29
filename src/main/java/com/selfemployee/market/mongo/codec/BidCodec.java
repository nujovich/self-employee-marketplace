package com.selfemployee.market.mongo.codec;

import com.selfemployee.market.model.Bid;
import com.selfemployee.market.mongo.converter.BidConverter;

import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BidCodec implements CollectibleCodec<Bid> {

    @Autowired
    private BidConverter bidConverter;

    private Codec<Document> documentCodec;

    public BidCodec(Codec<Document> documentCodec) {
        this.documentCodec = documentCodec;
    }

    @Override
    public void encode(BsonWriter writer, Bid value, EncoderContext encoderContext) {
        // TODO Auto-generated method stub

    }

    @Override
    public Class<Bid> getEncoderClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bid decode(BsonReader reader, DecoderContext decoderContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bid generateIdIfAbsentFromDocument(Bid document) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean documentHasId(Bid document) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BsonValue getDocumentId(Bid document) {
        // TODO Auto-generated method stub
        return null;
    }
    
}