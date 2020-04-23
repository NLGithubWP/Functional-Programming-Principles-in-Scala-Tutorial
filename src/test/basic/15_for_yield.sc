case class User(name: String, age: Int)

val userBase = List(User("a",1), User("b",2), User("c",3), User("d",4))

val twentySomethings = for (user <- userBase if (user.age > 20 && user.age < 30)) yield user.name

for (ele <- twentySomethings){
  println(ele)
}

twentySomethings.foreach(name => print(name))

def foo(n: Int, v:Int)={
  for (i <- 0 until n;
       j <- i until n if i+j ==v)
    yield {
      val ii = i*10
      val jj = i*10
      (ii,jj)
    }
}

foo(10, 10) foreach {
  case (i, j) => println(s"($i, $j) ")  // prints (1, 9) (2, 8) (3, 7) (4, 6) (5, 5)
}

//def foo(n: Int, v: Int) = {
//  for (i <- 0 until n;
//       j <- i until n if i + j == v)
//    yield (i, j)
//}
//
//foo(10, 10) foreach {
//  case (i, j) => println(s"($i, $j) ")  // prints (1, 9) (2, 8) (3, 7) (4, 6) (5, 5)
//}



