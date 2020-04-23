
//variables

val  x = 1+1
var x=2

//functions

(x:Int) => x+1
val addOne=(x:Int) => x+1
addOne(1)
val add = (x:Int, y:Int) => x+y
print(add(1,2))
val getanswer=()=>42

//methods

def getanswer:Int=42
def name:String=System.getProperty("user.name")
def add2(x:Int, y:Int):Int=x+y
def addTwo(x:Int):Int=x+2
def mutilines(x:Int, y:Int):Int={
  val square = x*y
  val squarepls = square*x*y
  squarepls
}
mutilines(2,3)

// class

class Greeter(prefix:String, suffix: String){
  def greet(name:String): String=
    {
      print(prefix+name+suffix)
      val res=" "+name+" "
      res
    }
}
val greeter = new Greeter("hellow", "!")
val res = greeter.greet("scala developer")
print(res)

//case class

case class Point(x:Int, y:Int)
val point = Point(1,2)
val anotherPoint = Point(1, 2)
// case class can compare each other
//if (point==anotherPoint){
//  print(point + " and "+anotherPoint +"are same")
//} else{
//  print(point + "and" + anotherPoint + "are not same")
//}

//object: single instance

object IdFactory{
  private var counter = 0
  def create():Int={
    counter += 1
    counter
  }
}
val newId:Int = IdFactory.create()

//trait

trait Greetert{
  def greet(name:String):Unit={
  print("trait test")
  }
}
class DefaultGreeter extends Greetert

class CustomizableGreeter(prefix:String, postfix:String) extends Greetert{
  override def greet(name:String):Unit={
    print("override")
  }
}
val greetert=new DefaultGreeter()
greetert.greet("scala developer")

val customergreet = new CustomizableGreeter("test", "finished")
customergreet.greet("yes")

object Main{
  def main(args:Array[String]):Unit=
    print("using main functions")
}




















