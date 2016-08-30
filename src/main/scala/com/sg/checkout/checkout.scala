package com.sg.checkout

trait CheckoutApp extends App {
  def printCheckout(price: String): Any = print(price)

  val priceMap = Map("apple" -> 0.60, "orange" -> 0.25)

  val checkout = new CheckoutCalculator(priceMap)
  val price = checkout.calculate(args.toList)
  printCheckout(s"£$price")
}

class CheckoutCalculator(val priceMap: Map[String, Double]) {
  def calculate(items: List[String]): Double = {
    items.foldRight(0.0)((item, acc) => priceMap(item.toLowerCase) + acc)
  }

}
