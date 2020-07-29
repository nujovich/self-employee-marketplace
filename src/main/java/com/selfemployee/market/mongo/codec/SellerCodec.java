package com.selfemployee.market.mongo.codec;

import com.selfemployee.market.model.Seller;
import com.selfemployee.market.mongo.converter.SellerConverter;

import org.bson.BsonObjectId;
import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;
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
        Document document = sellerConverter.convertToMongoDocument(value);
        documentCodec.encode(writer, document, encoderContext);

    }

    @Override
    public Class<Seller> getEncoderClass() {
        return Seller.class;
    }

    @Override
    public Seller decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        Seller seller = sellerConverter.convertToModel(document);
        return seller;
    }

    @Override
    public Seller generateIdIfAbsentFromDocument(Seller seller) {
        if(!documentHasId(seller)) {
            seller.setId(new ObjectId());
        }
        return seller;
    }

    @Override
    public boolean documentHasId(Seller seller) {
        return (seller.getId() != null);
    }

    @Override
    public BsonValue getDocumentId(Seller seller) {
        if(!documentHasId(seller)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonObjectId(seller.getId());
    }
    
}