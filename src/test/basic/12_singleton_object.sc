// the object will be created when it is used for first time

import scala.math._
//one instance and one class has the same name, they can use each other's private variables
case class Circle(r:Double) {
  import Circle._
  def area:Double= calculateArea(r)
}

object Circle{
  private def calculateArea(r:Double):Double = r*r
}

val circle1 = Circle(5.0)

circle1.area