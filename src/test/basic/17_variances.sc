
//Scala supports variance annotations of type parameters of generic classes
//must be covariant
class Foo[+A]
class Foo[-A]
class Foo[A]

// +A, if A is subtype of B, then List[A] is subtype of List[B]

abstract class Animal {
  def name: String
}
case class Cat(name: String) extends Animal
case class Dog(name: String) extends Animal















