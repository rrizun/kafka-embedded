package io.example;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.confluent.kafka.schemaregistry.rest.SchemaRegistryMain;

/**
 * Main
 */
@EnableScheduling
@SpringBootApplication
public class Main {
  public static void main(String... args) {
    SpringApplication.run(Main.class, args);
  }

  @EventListener
  public void applicationStartedEvent(ApplicationStartedEvent event) throws Exception {

    log("applicationStartedEvent");

    new Thread() {
      @Override
      public void run() {
        log("run");
        try {
          SchemaRegistryMain
              .main(new String[] { "/tmp/confluent.kSXDqvVg/schema-registry/schema-registry.properties" });
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }.start();

  }

  private void log(Object... args) {
    new LogHelper(this).log(args);
  }

}
