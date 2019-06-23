package WorkingWithObjects.finance1.finance.currencies

import WorkingWithObjects.finance1.finance.currencies.Currency.Currency

class Money(val amount: Int, val currency: Currency) {
  override def toString: String = s"$amount $currency"
}
