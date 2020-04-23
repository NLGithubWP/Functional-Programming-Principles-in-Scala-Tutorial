
"""
  |1. abc
  |   abstract classes can have method signatures defined in the class without actually having a definition. Removing the abstract keyword would cause an error.
  |   abstract classes cannot be instantiated.
"""


abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}
class NonEmptyIntSet(root: Int, leftSubtree: IntSet, rightSubtree: IntSet) extends IntSet {
  def incl(elem: Int): IntSet = {
    if (elem < root) new NonEmptyIntSet(root, leftSubtree.incl(elem), rightSubtree)
    else if (elem > root) new NonEmptyIntSet(root, leftSubtree, rightSubtree.incl(elem))
    else this
  }
  def contains(elem: Int): Boolean = {
    if (elem < root) leftSubtree.contains(elem)
    else if (elem > root) rightSubtree.contains(elem)
    else true
  }
  override def toString() = "{" + leftSubtree + root + rightSubtree + "}"
}

class EmptyIntSet extends IntSet {
  def incl(elem: Int): IntSet = new NonEmptyIntSet(elem, new EmptyIntSet, new EmptyIntSet)
  def contains(elem: Int): Boolean = false
  override def toString() = "."
}

val t1 = new NonEmptyIntSet(7, new EmptyIntSet, new EmptyIntSet)
val t11 = t1.incl(5)
val t12 = t11.incl(9)
val t13 = t12.incl(2)
val t14 = t13.incl(1)
val t15 = t14.incl(8)
val t16 = t15.incl(10)

val t2 = t1 incl 5 incl 9 incl 3 incl 2 incl 1 incl 8 incl 10
val t3 = t1.incl(5).incl(9).incl(3).incl(2).incl(1).incl(8).incl(10)


"""
  |2. Object definitions
  |  In the IntSet example, there is only a single empty IntSet.
  |  It is a overkill to allow users to create multiple instances of it.
  |  Such scenarios are better expressed using object definitions
  |  The below snippet defines a singleton object.
  |
  |   When a singleton object shares the same name as the class, it is called the class' companion object.
  |   The class is called the companion class of the singleton object.
  |   A class and it's companion object should be defined in the same source file.
  |   A class and it's companion object can access each others' private members.
  |   They are vals so EmptyIntSet evaluates to itself.
"""
object EmptyIntSet extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmptyIntSet(x, EmptyIntSet, EmptyIntSet) // We dropped the 'new'
}

"""
  |3. program
  |   Each Scala application contains an object with a main method.
  |   After compiling with scalac, it can be executed on the command line with scala <object-name>
|"""

/**
 * How Classes Are Organized
 */
"""
  |4.Classes and Objects are organised in packages.
  |5. Traits
  |   In Scala, a class can have only one superclass. It's a single inheritance language.
  |   If a class wants to inherit code from more than one supertype we use traits.
  |   traits are declared like an abstract class with the keyword trait instead of abstract class
  |   Classes, objects and traits can inherit from at most one class but from many traits.
  |   Classes inherit code from a trait with the with keyword.
  |
  |   ** traits can not have val parameters. But unlike interfaces in Java it can contain concrete methods and fields.
|"""

class Shape
trait Planar
trait Movable
class Square extends Shape with Planar with Movable

"""
  |6. Scala class hierarchy
  |   Scala.Any is at the top of scala class hierarchy and is the base type of all scala types. It is also the base type that defines universal methods like toString, equals, hashCode, ==, !=, etc
  |   Scala.AnyVal is a subclass of Scala.Any and is the base class of all primitive types like Byte, Int, Double, etc
  |   Scala.AnyRef is a base type of all reference types. Alias of java.lang.Object. scala.Iterable, scala.Seq, scala.List are base classes of scala.AnyRef but they also implement the trait Scala.ScalaObject
  |
  |
  |   Scala.Nothing is at the bottom of Scala's type hierarchy. It is a subtype of every other type. There is no value of type Nothing
  |   Used to signal abnormal termination. If sometimes a function throws an exception or terminates unexpectedly, it's return type is of type Nothing.
  |   Used as an element type of empty collections.
  |   An expression throw exp returns type Nothing
  |
  |
  |   Scala.Null is the subtype of every reference type.
  |   Null is a subtype of every class that inherits from java.lang.Object.; it is incompatible with subtypes of AnyVal.
  |   Every reference class type also has null as a value.
  |   The type of null is Null
  |
|"""

//if (true) 1 else false. The type of this expression is AnyVal. Looking at the hierarchy, the common super type of Int and Boolean is AnyVal.
val x = null
val y: String = x
//val z: Int = no       // type mismatch

/**
 * Polymorphism
 */

"""
  |7. Cons-Lists
  |   Concept of cons cell
  |       https://en.wikipedia.org/wiki/Cons
  |       https://cs.gmu.edu/~sean/lisp/cons/
  |   Immutable linked lists are a fundamental data structure in many FP languages. They are made up of:
  |   Cons - a cell containing an element and the remainder of the list.
  |   Nil - the empty list
  |
  |
  |8. Value paramters
  |
|"""

class Cons(val _head: Int, val _tail: Int)
class Cons(_head: Int, _tail: Int) {
  val head = _head
  val tail = _tail
}


"""
  |9. Type paramters
  |
  |"""

trait List[T]
class Cons[T](val _head: T, val _tail: List[T]) extends List[T]
class Nil[T] extends List[T]

/**
 * Type parameters can also be applied to functions, T is type parameters
 * type parameters do not affect evaluation in Scala. All type parameters and type arguments are removed before evaluating a program.
 * This is called type erasure.
 */

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton[Int](1)


"""
  |type parameters do not affect evaluation in Scala.
  |type erasure: we can assume that all type parameters and type arguments are removed before evaluating the program.
  |
  |languages that use type erasure: Scala, Java, Haskell, ML, Ocaml.
  |languages that keep the type parameters around run time: C++, C#, F#.
  |
  |polymorphsim has two principal forms: subtyping and generics:
  |subtyping: instances of a subclass can be passed to a base class.
  |generics: instances of a class or function are created by type parameterization.
  |
  |""".stripMargin
class TestPoly(x:Int) {
  def t1(a:Int): Unit ={
    print(a)
  }
  def t1(a:Int, b:Int):Unit={
    print(a,b)
  }
}

var tt = new TestPoly(1)

tt.t1(123)
tt.t1(123,22)

