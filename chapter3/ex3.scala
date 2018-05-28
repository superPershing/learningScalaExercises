val input = "cyan"
var output: String = ""
for (i <- input) {
    output = output + String.format("%04x", i.toInt)
}
println(output)

## TODO What?