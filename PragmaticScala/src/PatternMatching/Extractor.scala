package PatternMatching

object Extractor extends App {
  StockService process "GOOG"
  StockService process "GOOG:310.84"
  StockService process "GOOG:BUY"
  StockService process "IBM"
  StockService process "ERR:12.21"
}

object StockService {
  def process(input: String) = {
    input match {
      case Symbol() => println(s"Look up price for valid symbol $input")
      case ReceiveStockPrice(symbol @ Symbol(), price) => println(s"Received $$$price for symbol $symbol")
      case _ => println(s"Invalid input $input")
    }
  }
}

object Symbol {
  def unapply(symbol: String): Boolean = {
    symbol == "GOOG" || symbol == "IBM"
  }
}

object ReceiveStockPrice {
  def unapply(input: String): Option[(String, Double)] = {
    try {
      if (input contains ":") {
        val splitQuote = input split ":"
        Some((splitQuote(0), splitQuote(1).toDouble))
      } else {
        None
      }
    } catch {
      case _: NumberFormatException => None
    }
  }
}