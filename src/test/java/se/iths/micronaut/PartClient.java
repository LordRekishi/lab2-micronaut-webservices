package se.iths.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import se.iths.entity.Part;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Client("/parts")
public interface PartClient {

    @Post
    @NonNull
    HttpStatus save(@NonNull @NotNull @Valid Part part);

    @NonNull
    @Get
    List<Part> findAll();
}
