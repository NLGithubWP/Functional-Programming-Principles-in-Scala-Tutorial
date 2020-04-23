import scala.util.Random
//extractor must include unapply methods,


import scala.util.Random

// this is an object not a class
//Option[A] 是一个类型为 A 的可选值的容器： 如果值存在， Option[A] 就是一个 Some[A] ，
// 如果不存在， Option[A] 就是对象 None
object CustomerID {

  def apply(name: String) = s"$name--${Random.nextLong}"

  def unapply(customerID: String): Option[String] = {
    val stringArray: Array[String] = customerID.split("--")
    if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
  }
}

// CustomerID("Sukyoung")  is the same as CustomerID.apply("Sukyoung")

val customer1ID = CustomerID("Sukyoung")  // Sukyoung--23098234908
customer1ID match {
  case CustomerID(a) => println(a)  // prints Sukyoung
  case _ => println("Could not extract a CustomerID")
}


object Test{
  def apply(name: String):String=name

  def unapply(Test: String): Option[String] = {
    Some(Test)
  }
}

val test = Test("123")
val Test(a) = test
print(a)



