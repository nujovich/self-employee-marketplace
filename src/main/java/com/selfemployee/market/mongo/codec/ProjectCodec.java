package com.selfemployee.market.mongo.codec;

import com.selfemployee.market.model.Project;
import com.selfemployee.market.mongo.converter.ProjectConverter;

import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectCodec implements CollectibleCodec<Project> {

    @Autowired
    private ProjectConverter projectConverter;

    private Codec<Document> documentCodec;

    public ProjectCodec(Codec<Document> documentCodec) {
        this.documentCodec = documentCodec;
    }

    @Override
    public void encode(BsonWriter writer, Project value, EncoderContext encoderContext) {
        // TODO Auto-generated method stub

    }

    @Override
    public Class<Project> getEncoderClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Project decode(BsonReader reader, DecoderContext decoderContext) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Project generateIdIfAbsentFromDocument(Project document) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean documentHasId(Project document) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BsonValue getDocumentId(Project document) {
        // TODO Auto-generated method stub
        return null;
    }
    
}