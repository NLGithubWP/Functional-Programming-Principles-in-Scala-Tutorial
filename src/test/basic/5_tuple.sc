// tuple can store muti-types elements
val tuple_exp = ("suger", 25,"123"): Tuple3[String, Int, String]
print(tuple_exp._1)
print(tuple_exp._2)
print(tuple_exp._3)

val (name, number, other) = tuple_exp
print(name, number, other)

val list_exp = List(("memory", 12.2), ("other", 123.2))

list_exp.foreach{tuple => {
  tuple match {
    case ("memory", distance) => print(s"the distance is $distance")
    case p if (p._1 == "other") => print(s"the distance is $p._2")
    case _ => print("not matched")
  }}
}

val numpar = List(("t1", 1), ("t2", 2))

for((a,b) <- numpar){
  print(a, b)
}

