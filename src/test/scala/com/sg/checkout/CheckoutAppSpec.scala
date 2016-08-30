package com.sg.checkout

import org.scalatest.{FlatSpec, Matchers}

class CheckoutAppSpec  extends FlatSpec with Matchers {

  "CheckoutApp" should "calculate the total cost" in {
    val app = new CheckoutApp with MockOutput
    app.start(Array("Apple", "Apple", "Orange", "Apple"))
    app.messages.head shouldBe "£1.45"
  }
}
