package WorkingWithObjects

class VCar(override val id: Int, override val year: Int, val fuelLevel: Int) extends Vehicle(id, year) {
  override def toString: String = s"${super.toString} Fuel Level: $fuelLevel"
}


class Vehicle(val id: Int, val year: Int) {
  override def toString: String = s"ID: $id Year: $year"
}

object UseVehicle extends App {
  val car = new VCar(1, 2015, 100)
  println(car)
}
