package com.selfemployee.market.mongo.codec;

import com.selfemployee.market.model.Seller;
import com.selfemployee.market.mongo.converter.SellerConverter;

import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerCodec implements CollectibleCodec<Seller> {

    @Autowired
    private SellerConverter sellerConverter;

    private Codec<Document> documentCodec;

    public SellerCodec(Codec<Document> documentCodec) {
        this.documentCodec = documentCodec;
    }

    @Override
    public void encode(BsonWriter writer, Seller value, EncoderContext encoderContext) {
        // TODO Auto-generated method stub

    }

    @Override
    public Class<Seller> getEncoderClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Seller decode(BsonReader reader, DecoderContext decoderContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Seller generateIdIfAbsentFromDocument(Seller document) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean documentHasId(Seller document) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BsonValue getDocumentId(Seller document) {
        // TODO Auto-generated method stub
        return null;
    }
    
}