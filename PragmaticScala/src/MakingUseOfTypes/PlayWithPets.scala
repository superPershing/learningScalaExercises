package MakingUseOfTypes

object PlayWithPets extends App {
  def workWithPets[T <: Pet](pets: Array[T]) = {
    println("Playing with pets: " + pets.mkString(", "))
  }

  val dogs = Array(new Dog("Rover"), new Dog("Comet"))

  workWithPets(dogs)


  def copyPets[S, D >: S](fromPets: Array[S], toPets: Array[D]) = {}

  val pets = new Array[Pet](10)
  copyPets(dogs, pets)
}

class Pet(val name: String) {
  override def toString: String = name
}

class Dog(override val name: String) extends Pet(name)

