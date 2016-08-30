package com.sg.checkout

object CheckoutApp extends App {
  new CheckoutApp().start(args)
}

class CheckoutApp extends Output {
  def start(args: Array[String]): Unit = {
    val priceMap = Map(
      "apple" -> Map(1 ->0.60, 2-> 0.60),
      "orange" -> Map(1->0.25, 3->0.5)
    )

    val checkout = new CheckoutCalculator(priceMap)
    val price = checkout.calculate(args.toList)
    print(s"£$price")
  }
}


class CheckoutCalculator(val priceMap: Map[Item, Map[Count, Price]]) {

  def calculate(items: List[String]): Price = {
    val groupedItems = items.groupBy(identity).mapValues(_.size)
    groupedItems.foldRight(0.0)((item, acc) => getPriceWithDiscount(item._1, item._2) + acc)
  }

  private def getPriceWithDiscount(item: Item, itemCount: Count) = {
    def calculate(count: Int, priceRules: Seq[(Count, Price)], acc: Price): Price = {
      priceRules
        .headOption
        .map(r => calculate(count % r._1, priceRules.tail, acc + (count / r._1) * r._2))
        .getOrElse(acc)
    }
    calculate(itemCount, priceRules(item).toSeq.sortWith(_._1  > _._1 ), 0)
  }

  private def priceRules(item: Item): Map[Count, Price] =
    priceMap.getOrElse(item.toLowerCase, throw new IllegalArgumentException(s"Unknown item $item"))
}
