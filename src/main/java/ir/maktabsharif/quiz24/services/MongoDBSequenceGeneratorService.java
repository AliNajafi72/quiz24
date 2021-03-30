package ir.maktabsharif.quiz24.services;

import ir.maktabsharif.quiz24.entities.mongodb.MongoDBSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class MongoDBSequenceGeneratorService {

    private MongoOperations mongoOperations;

    @Autowired
    public MongoDBSequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String sequenceName) {
        Optional<MongoDBSequence> sequenceOptional = Optional.ofNullable(
                mongoOperations.findAndModify(
                        Query.query(where("_id").is(sequenceName)),
                        new Update().inc("seq",1),
                        options().returnNew(true).upsert(true),
                        MongoDBSequence.class
                )
        );
        return sequenceOptional.isPresent() ? sequenceOptional.get().getSeq() : 1L;
    }

}
