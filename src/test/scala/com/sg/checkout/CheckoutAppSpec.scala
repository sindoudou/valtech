package com.sg.checkout

import org.scalatest.{FlatSpec, Matchers}

class CheckoutAppSpec  extends FlatSpec with Matchers {

  "CheckoutApp" should "calculate the total cost" in {
    val app = new TestApp()
    app.main(Array("Apple", "Apple", "Orange", "Apple"))
    app.totalPrice shouldBe "£2.05"
  }

  class TestApp extends CheckoutApp {
    var totalPrice: String = _
    override def printCheckout(price: String) = totalPrice = price
  }
}
