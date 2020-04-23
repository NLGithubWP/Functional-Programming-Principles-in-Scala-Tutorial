
abstract class A {
  val message: String
}

class B extends A {
  val message = "this is the test"
}

trait C extends A {
  def loudmessage:String = message.toUpperCase()

}

class D extends B with C

val d = new D
print(d.message)
print(d.loudmessage)

// example 2:

abstract class Abstest{
  type T
  def hasNext: Boolean
  def next(): T
}

class StringIterator(s: String) extends Abstest{
  override type T = Char
  private var i = 0
  def hasNext = i < s.length
  def next(): Char = {
    val ch = s charAt i
    i += 1
    ch
  }
}
val sitest = new StringIterator("test")
print(sitest.next())

// f is applied to the return value of method "next"
trait RichIterator extends Abstest {
  //f is a function which takes a single parameter of type T and
  //returns Unit.
  def df(f: T => Unit): Unit = while (hasNext) f(next())
}

object StringIteratorTest extends App {
  class RichStringIter extends StringIterator("scala") with RichIterator
  val richStringIter = new RichStringIter
  richStringIter.df(println)
  richStringIter df println
};












