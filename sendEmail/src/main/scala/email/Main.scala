import courier._, Defaults._
object Main extends App {
  val senderAddress = "xxx@163.com"
  val password = "yourpassword"
  val mailer = Mailer("smtp.163.com", 465)
    .auth(true)
    .as(senderAddress, password)
    .ssl(true)()
  mailer(Envelope.from(senderAddress.split("@")(0) `@` senderAddress.split("@")(1))
    .to("727342027" `@` "qq.com")
    .subject("finish xxx task")
    .content(Text("finish xxx task")))
    .onSuccess { case _ => println("message delivered") }
}
