package UsingTraits

object UseCat extends App {
  def useFriend(friend: Friend) = friend.listen

  val alf = new Cat("Alf") with Friend
  val friend: Friend = alf
  alf.listen

  useFriend(friend)
}
