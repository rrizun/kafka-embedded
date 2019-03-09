package io.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

class HelloWorldResponse {
  public final String world;
  public HelloWorldResponse(String world) {
    this.world = world;
  }
}

@RestController
public class MyRestController {

  @GetMapping("/hello")
  public HelloWorldResponse hello() {
    return new HelloWorldResponse("world!");
  }

}