package me.wonwoo.web;

import me.wonwoo.domain.Beer;
import me.wonwoo.generator.BeerGenerator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Created by wonwoolee on 2017. 4. 26..
 */
@Controller
public class BeerController {

  private final BeerGenerator beerGenerator;

  public BeerController(BeerGenerator beerGenerator) {
    this.beerGenerator = beerGenerator;
  }

  @GetMapping("/")
  public String index() {
    return "beer";
  }

  @GetMapping(value = "/beers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseBody
  public Flux<Beer> events() {
    return beerGenerator.generatorStream(Duration.ofMillis(500));
  }
}
