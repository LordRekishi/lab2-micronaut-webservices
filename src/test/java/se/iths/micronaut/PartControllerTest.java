package se.iths.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import se.iths.entity.Part;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PartControllerTest implements TestPropertyProvider {

    @Inject
    PartClient client;

    @Test
    @DisplayName("Parts Endpoint Interact with MongoDB")
    void partsEndpointInteractWithMongoDb() {

        List<Part> parts = client.findAll();
        assertTrue(parts.isEmpty());

        HttpStatus status = client.save(new Part("Handlebar"));
        assertEquals(HttpStatus.CREATED, status);

        parts = client.findAll();
        assertFalse(parts.isEmpty());
        assertEquals("Handlebar", parts.get(0).getName());
        assertNull(parts.get(0).getDescription());

        status = client.save(new Part("Headlight", "Lights up the environment"));
        assertEquals(HttpStatus.CREATED, status);

        parts = client.findAll();
        assertTrue(parts.stream().anyMatch(part -> "Lights up the environment".equals(part.getDescription())));
    }

    @AfterAll
    static void cleanup() {
        MongoDbUtils.closeMongoDb();
    }

    @Override
    public Map<String, String> getProperties() {
        MongoDbUtils.startMongoDb();
        return Collections.singletonMap("mongodb.uri", MongoDbUtils.getMongoDbUri());
    }
}
