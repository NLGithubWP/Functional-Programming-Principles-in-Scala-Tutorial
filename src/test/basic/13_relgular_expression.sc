import scala.util.matching.Regex

val numpat : Regex = "[0-9]".r

for (matchres <- numpat.findAllIn("asdf123")) {
    print(matchres)
}

