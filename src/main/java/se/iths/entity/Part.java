package se.iths.entity;

import io.micronaut.core.annotation.*;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;

@Introspected
@ReflectiveAccess
public class Part {

    @NonNull
    @NotBlank
    @BsonProperty("name")
    private final String name;

    @Nullable
    @BsonProperty("description")
    private final String description;

    @Creator
    @BsonCreator
    public Part(@NonNull @BsonProperty("name") String name,
                @Nullable @BsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    public Part(@NonNull String name) {
        this(name, null);
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }
}
