package com.sg.checkout

import org.scalatest._

class CheckoutCalculatorSpec extends FlatSpec with Matchers {

  "Checkout" should "calculate the total cost" in {
    val priceMap = Map(
      "apple" -> Map(1 -> 0.60),
      "orange" -> Map(1 -> 0.25))

    new CheckoutCalculator(priceMap).calculate(List("Apple", "Apple", "Orange", "Apple")) shouldBe 2.05
  }

  "Checkout" should "handle buy one get one free" in {

    val priceMap = Map("apple" -> Map(1 -> 0.60, 2 -> 0.60))

    new CheckoutCalculator(priceMap).calculate(List("Apple", "Apple")) shouldBe 0.60
  }

  "Checkout" should "handle three for the price of two" in {
    val priceMap = Map(
      "apple" -> Map(1 ->0.60, 2-> 0.60),
      "orange" -> Map(1->0.25, 3->0.5))

    new CheckoutCalculator(priceMap).calculate(List("Orange", "Orange", "Orange")) shouldBe 0.50
  }

  "Checkout" should "calculate the total cost with discount" in {
    val priceMap = Map(
      "apple" -> Map(1 ->0.60, 2-> 0.60),
      "orange" -> Map(1->0.25, 3->0.5))

    new CheckoutCalculator(priceMap).calculate(List("Apple", "Apple", "Orange", "Apple")) shouldBe 1.45
  }
}
