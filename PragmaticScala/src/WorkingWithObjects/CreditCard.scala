package WorkingWithObjects

object UseCreditCard extends App {
  val creditCard = new CreditCard(10, 20)
  creditCard.creditLimit += 10
  println(creditCard.number)
  println(creditCard.creditLimit)
}

class CreditCard(val number: Int, var creditLimit: Int)
