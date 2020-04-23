
// Any is super class for any type
val list: List[Any] = List(
  "a string",
  732,
  'c',
  true,
  ()=> "an anonymous function returing a string"
)
list.foreach(element=>print(element))

//type transfer
// byte -> short -> Int -> Long -> Float -> Double
// Char -> Int

val x: Long=123123
val y: Float=x
val z: Double=y


