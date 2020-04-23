/**
 * Lecture 6.1 - Other Collections
 *    Vector
 *  If the use case requires using the head or tail of a list and recursing then List is a better data structure.
 *  If we have bulk operations like map, fold or filter then, Vector is a better data structure.
 *
 *  Vectors support all of the same operations of Lists except ::. Instead of x :: xs there are:
     * x :+ xs which adds the element x to the end of xs
     * x +: xs which adds the element x to the beginning of xs
     * In both the notations above, : points to the sequence.
 *
 */

val nums = Vector(1,2,3)

nums :+ 9
10 +: nums

/**
  Collection hierarchy
    The base class of List and Vector is Seq, the class of all sequences.

    Seq itself is a sub class of Iterable

                  Iterator
                  /   |   \
               Seq   Set  Map
             /  |  \\
            /   |   \ IndexedSeq
           /    |   /\  |
        List  Vector  Range

    Seq, Set and Map are sub-classes of Iterator.
    List, Vector, Range and IndexedSeq are sub-classes of Seq
    Vector and Range are sub-classes of IndexedSeq
    Arrays and Strings support the same operations as Seq and can be converted to sequences when needed.

    Another Seq is Range. Three operators are defined on Range:

      to: 1 to 5 would give scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
      until: 1 until 5 would give scala.collection.immutable.Range = Range(1, 2, 3, 4)
      by: 1 to 10 by 3 would give scala.collection.immutable.Range = Range(1, 4, 7, 10)\

Sequence operations
    xs exists p
    xs forall p
    xs zip ys
    xs.unzip
    xs.flatMap f
    xs.sum
    xs.product
    xs.max
    xs.min
 */

val res = Vector(1,2,3,1,2,3,1,2,3,1,2,3)
res.forall(_+1 >0)
res.exists(_+1 >0)
res.sum
res.product
res.max
res.min

/**
 * Lecture 6.2 - Combinatorial Search and For-Expressions
 */

object pairs {
  val n = 7
  val res = (1 until n).map(i => (1 until i).map(j => i+j))
  val res2 = (1 until n).flatMap(i => (1 until i).map(j => i+j))
}

pairs.res
// res is the same as res2, because xs.flatMap = xs.map(f).flatten
pairs.res.flatten
pairs.res2


/**
 * for Expressions
  for ( s ) yield e
    s is a sequence of generators and filters.
    e is an expression whose value is returned by an iteration.
    A generator is of the form p <- c where p is a pattern and c is an expression whose value is a collection.
    A filter is of the form if f where f is a boolean expression.
    The sequence must start with a generator.
    OF there are several generators in the sequence, the last generator vary faster than the first.
    Instead of ( s ), { s } can also be used and then the sequence of generators and filters can be written on multiple lines without using semicolons.
 */

object pairs2{
  def isPrime(n:Int): Boolean = (2 until n).forall(n %_ !=0)

  def test(n:Int) = {
    for {
      i <- 1 until n
      j <- 1 until i
      if isPrime(i+j)
    } yield (i, j)
  }
}

pairs2.test(20)


/**
 * Sets
     * Sets are a sub-class of Iterables.
     * They are defined analogously to a sequence: val s = Set('b', 'a', 'c')
     * Most operations on Sequences are also available on sets:
 *
 * Differences between Sets and Sequences are:
     * Sets are unordered.
     * Sets do not have duplicate elements.
     * Fundamental operation on Sets is contains.
     * N queens problem example.
 */

val s = Set(1,2,3)
s.map(_+2)
s.filter(_>0)
s.nonEmpty


/**
 * Maps
   Maps are special in Scala since they are both iterables and functions.
 */

val mapper = Map("a" -> 1, "b" ->2)

//mapper("c")
mapper.contains("a")


/**
 * Option
   An Option type is defined as:
 */

trait Option[+A]
case class Some[+A](value: A) extends Option[A]
object None extends Option[Nothing]


/**
 * Sorted and GroupBy
 */

val fruits = List("apple", "pear", "orange", "pineapple")
fruits.sortWith(_.length < _.length) // gives List("pear", "apple", "orange", "pineapple")
fruits.sorted

fruits.groupBy(_.head)