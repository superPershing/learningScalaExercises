package UsingTraits

object UseFriend extends App {
  val john = new Man("John")
  val sara = new Woman("Sara")
  val comet = new Dog("Comet")

  john.listen
  sara.listen
  comet.listen

  val mansBestFriend = comet
  mansBestFriend.listen

  def helpAsFriend(friend: Friend) = friend.listen
  helpAsFriend(sara)
  helpAsFriend(comet)
}
