package me.wonwoo.generator;

import me.wonwoo.domain.Beer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by wonwoolee on 2017. 4. 26..
 */
@Service
public class BeerGenerator {

  private final static List<Beer> GENERATOR = Arrays.asList(
      new Beer("Heineken", new BigDecimal(67.85, new MathContext(2))),
      new Beer("Hoegaarden", new BigDecimal(45.19, new MathContext(2))),
      new Beer("Miller", new BigDecimal(24.89, new MathContext(2))),
      new Beer("Guinness", new BigDecimal(84.37, new MathContext(2))),
      new Beer("Corona", new BigDecimal(43.93, new MathContext(2))),
      new Beer("Budweiser", new BigDecimal(34.75, new MathContext(2)))
  );

  public Flux<Beer> generatorStream(Duration duration) {
    return Flux.generate(() -> 0,
        (BiFunction<Integer, SynchronousSink<Beer>, Integer>) (index, sink) -> {
          sink.next(GENERATOR.get(index));
          return (index + 1) % GENERATOR.size();
        })
        .zipWith(Flux.interval(duration))
        .map(t2 -> t2.getT1().copyRandom())
        .share()
        .log();
  }
}
