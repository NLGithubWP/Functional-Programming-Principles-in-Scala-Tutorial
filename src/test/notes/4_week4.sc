import scala.collection.mutable.ListBuffer

/**
 *1. Functions as objects
  *Functions types are treated as classes and function values as objects.
  *Functions in scala are objects with apply methods.
  *A function call like f(a, b) is expanded to f.apply(a, b)
  *in python, it's .__call__
  *eg:
 **/



val fun1 = (x: Int) => x * x

class AnonFunc extends Function1[Int, Int] {
  def apply(x: Int) = x * x
 }
val func2 = new AnonFunc

// those 2 are the same
func2(2)
func2.apply(2)

/**
 2. Exercise
  For the IntList class previously defined, define 3 functions in it such that users can create lists of length 0, 2 by
  doing IntList(), IntList(1) and IntList(1, 2)
 */


trait List[T] {
 def isEmpty: Boolean
 def head: T
 def tail: List[T]
}

class ConsCell[T](val head: T, val tail: List[T]) extends List[T] {
 def isEmpty: Boolean = false
}

class NilCell[T] extends List[T] {
 def isEmpty: Boolean = true
 def head: Nothing = throw new NoSuchElementException("Nil head")
 def tail: Nothing = throw new NoSuchElementException("Nil tail")
}

object List {
 def apply[T](): List[T] = new NilCell[T]
 def apply[T](a: T): List[T] = new ConsCell(a, new NilCell)
 def apply[T](a: T, b: T): List[T] = new ConsCell[T](a, new ConsCell(b, new NilCell))
}

val a = List(0,2).head


/**
 3. Subtyping and Generics
   The two types of polymorphism are:
      Subtyping - Allows us to pass instances of a subtype where a base type was required.
      Generics - Allows us to parameterize types with other types.
   https://www.jianshu.com/p/21602c973314
      B是A的子类，A是B的父类。

      当我们定义一个协变类型List[A+]时，List[Child]可以是List[Parent]的子类型。
      当我们定义一个逆变类型List[-A]时，List[Child]可以是List[Parent]的父类型。
      Scala的协变
      看下面的例子：

 **/

class Father{}
class Son extends Father{}
//协变
/**
 * cov不能赋值给cov2，因为Covariant定义成不变类型。
 */
class Outer[T](t:T){}
val cov1:Outer[Son] = new Outer[Son](new Son)
//val cov2:Covariant[Father] = cov1

/**
 * 因为Outer2定义成协变类型的，所以Outer2[Son]是Outer2[Father]的子类型，所以它可以被赋值给cov2。
 */
class Outer2[+T](t:T){}
val cov11:Outer2[Son] = new Outer2[Son](new Son)
val cov22:Outer2[Father] = cov11

/**
 * Outer3[-T]定义成逆变类型，Outer3[Father] 被看作 Outer3[Son]的子类型，故c可以被赋值给c2。
 */
class Outer3[-T](t:T){}
val cov111:Outer3[Father] = new Outer3[Father](new Father)
val cov222:Outer3[Son] = cov111



/**
 * 编译会出错。出错信息为 "Covariant type T occurs in contravariant position in type T of value t"。
 * 但是如果返回结果为类型参数则没有问题。
 * 为了在方法的参数中使用类型参数，你需要定义下界：
 * 对于这里最外层类[+T]是协变, 但是到了方法的类型参数时, 该位置发生了翻转, 成为-逆变的位置,
 * 所以你把T给他, 就会报错说你把一个协变类型放到了一个逆变的位置上
 *
 */
//class Consumer[+T](t: T) {
// def use(t: T) = {}
//}
class Consumer[+T](t: T) {
 def use[U >: T](u : U) = {println(u)}
}

//class Consumer2[-T](t: T) {
// def get[U <: T](): U = {new U}
//}

//class Animal {}
//class Bird extends Animal {}
//class Consumer3[-S,+T]() {
// def m1[U >: T](u: U): T = {new T} //协变，下界
// def m2[U <: S](s: S): U = {new U} //逆变，上界
//}

//class Test {
// val c:Consumer3[Animal,Bird] = new Consumer3[Animal,Bird]()
// val c2:Consumer3[Bird,Animal] = c
// c2.m1(new Animal)
// c2.m2(new Bird)
//}

/**
 * case class and pattern matching
 */

object Test {
 trait Expr
 case class Number(n: Int) extends Expr
 case class Sum(e1: Expr, e2: Expr) extends Expr
 case class Prod(e1: Expr, e2: Expr) extends Expr
 case class Var(name: String) extends Expr

 def nestOperations(e1: Expr, e2: Expr): String = {
  val l = e1 match {
   case Number(n) => n.toString
   case Prod(x, y) => show(x) + " * " + show(y)
   case Var(s) => s
   case Sum(x, y) => "(" + show(x) + " + " + show(y) + ")"
  }
  val r = e2 match {
   case Number(n) => n.toString
   case Prod(x, y) => show(x) + " * " + show(y)
   case Var(s) => s
   case Sum(x, y) => "(" + show(x) + " + " + show(y) + ")"
  }
  l + " * " + r
 }

 def show(e: Expr): String = e match {
  case Number(n) => n.toString
  case Sum(e1, e2) => show(e1) + " + " + show(e2)
  case Prod(e1, e2) => nestOperations(e1, e2)
  case Var(s) => s
 }
 show(Sum(Number(1), Number(2)))
 show(Sum(Prod(Number(2), Var("x")), Var("y")))
 show(Prod(Sum(Number(2), Var("x")), Var("y")))
}
