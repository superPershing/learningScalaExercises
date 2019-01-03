for (num <- 1 to 100) {
    val result = num match {
        case i if ((i%3 == 0) && (i%5 == 0)) => "typesafe"
        case i if (i%3 == 0) => "type"
        case i if (i%5 == 0) => "safe"
        case other => num
    }
    println(result)
}