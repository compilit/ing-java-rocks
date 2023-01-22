package nl.ing.java.rocks.application;

import static ru.yandex.qatools.embed.postgresql.distribution.Version.Main.V9_6;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

abstract class IntegrationTest {

  private static final EmbeddedPostgres postgres = new EmbeddedPostgres(V9_6);

  @BeforeAll
  static void setup() throws IOException {
    postgres.start("localhost", 5555, "java-rocks", "user", "password");
  }

  @AfterAll
  static void destroy() {
    postgres.stop();
  }
}
