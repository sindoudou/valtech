package com.sg.checkout

import org.scalatest._

class CheckoutCalculatorSpec extends FlatSpec with Matchers {
  "Checkout" should "calculate the total cost" in {
    val priceMap = Map("apple" -> 0.60, "orange" -> 0.25)

    new CheckoutCalculator(priceMap).calculate(List("Apple", "Apple", "Orange", "Apple")) shouldBe 2.05
  }
}
