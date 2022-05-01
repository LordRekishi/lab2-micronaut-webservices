package se.iths.controller;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import se.iths.entity.Part;
import se.iths.repository.PartRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/parts")
public class PartController {

    private final PartRepository partRepository;

    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Get
    Publisher<Part> list() {
        return partRepository.list();
    }

    @Post
    Mono<HttpStatus> save(@NonNull @NotNull @Valid Part part) {
        return partRepository.save(part)
                .map(added -> added ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }
}
