package se.iths.repository;

import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import se.iths.entity.Part;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface PartRepository {

    @NonNull
    Publisher<Part> list();

    Mono<Boolean> save(@NonNull @NotNull @Valid Part part);
}
