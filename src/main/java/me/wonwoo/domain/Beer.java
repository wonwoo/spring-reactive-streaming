package me.wonwoo.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;
import java.util.Random;

/**
 * Created by wonwoolee on 2017. 4. 26..
 */
public class Beer {

  private static final MathContext MATH_CONTEXT = new MathContext(2);

  private String name;
  private BigDecimal price;
  private Instant instant = Instant.now();


  public Beer(String name, BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public Instant getInstant() {
    return instant;
  }

  public Beer copyRandom() {
    Beer beer = new Beer(this.name, randomPrice());
    beer.instant = Instant.now();
    return beer;
  }

  public BigDecimal randomPrice() {
    return getPrice().add(getPrice().multiply(
        new BigDecimal(0.5 * new Random().nextDouble(), MATH_CONTEXT)));
  }

  @Override
  public String toString() {
    return "Beer{" +
        "name='" + name + '\'' +
        ", price=" + price +
        ", instant=" + instant +
        '}';
  }
}
