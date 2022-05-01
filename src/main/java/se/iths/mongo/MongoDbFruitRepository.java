package se.iths.mongo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import se.iths.entity.Part;
import se.iths.repository.PartRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
public class MongoDbFruitRepository implements PartRepository {

    private final MongoDbConfiguration configuration;
    private final MongoClient client;

    public MongoDbFruitRepository(MongoDbConfiguration configuration, MongoClient client) {
        this.configuration = configuration;
        this.client = client;
    }

    @Override
    public Mono<Boolean> save(@NonNull @NotNull @Valid Part part) {
        return Mono.from(getCollection().insertOne(part))
                .map(result -> true)
                .onErrorReturn(false);
    }

    @Override
    public Publisher<Part> list() {
        return getCollection().find();
    }

    @NonNull
    private MongoCollection<Part> getCollection() {
        return client.getDatabase(configuration.getName())
                .getCollection(configuration.getCollection(), Part.class);
    }
}
