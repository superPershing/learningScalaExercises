val num: Double = 3.14159
val result = num match {
    case s if s > 0 => "greater"
    case s if s == 0 => "same"
    case s if s <0 => "less"
}
println(result)

val num2: Double = -5.42342
val result2 = {
    if (num2 > 0) {
        "greater"
    } else if (num2 == 0) {
        "same"
    } else {
        "less"
    }
}
println(result2)