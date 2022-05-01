package se.iths.micronaut;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoDbUtils {

    static MongoDBContainer container;

    public static void startMongoDb() {
        if (container == null) {
            container = new MongoDBContainer(DockerImageName.parse("mongo:latest"))
                    .withExposedPorts(27017);
        }
        if (!container.isRunning()) {
            container.start();
        }
    }

    public static String getMongoDbUri() {
        if (container == null || !container.isRunning()) {
            startMongoDb();
        }
        return container.getReplicaSetUrl();
    }

    public static void closeMongoDb() {
        if (container != null) {
            container.close();
        }
    }
}
