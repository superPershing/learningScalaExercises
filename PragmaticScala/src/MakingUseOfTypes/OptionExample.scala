package MakingUseOfTypes

object OptionExample extends App {
  def commentOnPractice(input: String) = {
    if (input == "test") Some("good") else None
  }

  for (input <- Set("test", "hack")) {
    val comment = commentOnPractice(input)
    val commentDisplay = comment.getOrElse("Found no comments")
    println(s"input: $input comment: $commentDisplay")
  }
}
