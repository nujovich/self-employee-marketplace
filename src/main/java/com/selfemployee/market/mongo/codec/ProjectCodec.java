package com.selfemployee.market.mongo.codec;

import com.selfemployee.market.model.Project;
import com.selfemployee.market.mongo.converter.ProjectConverter;

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
 * Seller codec class to decode/encode information
 */
public class ProjectCodec implements CollectibleCodec<Project> {

    @Autowired
    private ProjectConverter projectConverter;

    private Codec<Document> documentCodec;

    public ProjectCodec(Codec<Document> documentCodec) {
        this.documentCodec = documentCodec;
    }

    @Override
    public void encode(BsonWriter writer, Project value, EncoderContext encoderContext) {
        Document document = projectConverter.convertToMongoDocument(value);
        documentCodec.encode(writer, document, encoderContext);

    }

    @Override
    public Class<Project> getEncoderClass() {
        return Project.class;
    }

    @Override
    public Project decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        Project project = projectConverter.convertToModel(document);
        return project;
    }

    @Override
    public Project generateIdIfAbsentFromDocument(Project project) {
        if (!documentHasId(project)) {
            new Project.ProjectBuilder(project.getDescription(), project.getBudget(), project.getEndDateForBids())
            .setId(new ObjectId()).build();
        }
        return project;
    }

    @Override
    public boolean documentHasId(Project project) {
        return (project.getId() != null);
    }

    @Override
    public BsonValue getDocumentId(Project project) {
        if (!documentHasId(project)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonObjectId(project.getId());
    }
    
}