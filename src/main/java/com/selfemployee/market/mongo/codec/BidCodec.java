package com.selfemployee.market.mongo.codec;

import com.selfemployee.market.model.Bid;
import com.selfemployee.market.mongo.converter.BidConverter;

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

/**
 * Bif codec class to encode/decode information
 */
public class BidCodec implements CollectibleCodec<Bid> {

    @Autowired
    private BidConverter bidConverter;

    private Codec<Document> documentCodec;

    public BidCodec(Codec<Document> documentCodec) {
        this.documentCodec = documentCodec;
    }

    @Override
    public void encode(BsonWriter writer, Bid value, EncoderContext encoderContext) {
        Document document = bidConverter.convertToMongoDocument(value);
        documentCodec.encode(writer, document, encoderContext);
    }

    @Override
    public Class<Bid> getEncoderClass() {
        return Bid.class;
    }

    @Override
    public Bid decode(BsonReader reader, DecoderContext decoderContext) {
        return null;
    }

    @Override
    public Bid generateIdIfAbsentFromDocument(Bid bid) {
        if (!documentHasId(bid)) {
            bid.setId(new ObjectId());
        }
        return bid;
    }

    @Override
    public boolean documentHasId(Bid bid) {
        return (bid.getId() != null);
    }

    @Override
    public BsonValue getDocumentId(Bid bid) {
        if (!documentHasId(bid)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonObjectId(bid.getId());
    }
    
}