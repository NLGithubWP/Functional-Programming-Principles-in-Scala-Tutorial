
/**
 * Lists
 *  Lists are immutable. Arrays are mutable.
 *  Lists and Arrays are homogeneous i.e., elements of a list must be all of the same type.
 */

val l1 = List("d", "a", "b")
val l1first = l1(1)
val l2 = List(1,2,3)
val l3 = List(List(1,2), List(3,4))

//:: is used to add a element to a list from left side

val l11 = "1" :: ("2"::("3"::Nil))

//Operators ending with : associate to the right. So the cons operator :: is a method of the right operand. The expression

val nums = 1 :: 2 :: 3 :: Nil
//is equivalent to

Nil.::(3).::(2).::(1)


/**
 * Lecture 5.1 - More Functions on Lists
 *
  The three fundamental operations on Lists are isEmpty, head and tail
  Sublists and element access on lists
    xs.length
    xs.last
    xs.init - All list containing all elements of xs except the last one.
    xs take n - A list consisting of first n elements of xs or xs if xs has elements fewer than n.
    xs drop n - Rest of the elements of xs as a list after takeing n elements.
    xs(n) - Or xs apply n. The element of xs at index n.

  Creating new lists
    xs ++ ys - List containing all elements of xs followed by all elements of ys
    xs.reverse
    xs updated (n, x) - List containing all elements as xs except with x at the index n

  Finding elements
    xs indexOf n - Index of the first element matching n, -1 otherwise.
    xs contains n - xs indexOf n >= 0
 */

//val l1drop = l1.drop(3)
//val l1take = l1.take(2)
l1.length
l1.last

var l12 = l1 ++ l11
l12.reverse

l12.updated(1, "abc")
l12.indexOf("1")
l12.contains(12)

/**
 * A case is able to deconstruct a value (here xs) into its parts according to a pattern, if that "matches" (see it?).
 * Here a list is deconstructed into a head (giving it the name y) and a tail/rest (giving it the name ys).
 * Those two parts can now be accessed separately in the expression after the arrow.
 */

/**
 * Lecture 5.2 - Pairs and Tuples
 * Pair in Scala is written as (x, y)
 */

val pair = ("anser", 42)
val (label, value) = pair
val p1 = pair._1

/**
 * Lecture 5.3 - Implicit Parameters
 */

def merge[T](xs: List[T], ys:List[T]): List[T]
// 无法比较T，T类型未知，此时加入自定义比较方法

def merge2[T](xs:List[T], ys:List[T])(comp:(T,T) => Boolean):List[T] = {
  if (comp(xs.head, ys.head)) xs
}

print(merge2[Int](List(1, 3, 5), List(2, 4, 6))((x: Int, y: Int) => x < y))

//A further improvement to this would be to mark the ordering function parameter to merge as implicit:

def merge[T](xs: List[T], ys: List[T])(implicit ord: Ordering[T])

//The benefit of doing this is that we no longer need to pass an additional parameter from the merge function call. We can just do:
//  merge(List(1, 3, 5), List(2, 4, 6))
//and the compiler will deduce the appropriate Ordering type by looking at the parameter types in the call to merge

/**
 * Lecture 5.4 - Higher-Order List Functions
 * Some of the recurring patterns in computations on lists involves:
     * Transforming each element of the list in a certain way.
     * Retrieving a list of all elements satisfying a certain criteria.
     * Combining the elements of a list using an operator.
 * Higher order functions makes this easier
 *
 * Variants of filter:
 * filterNot - Elements of the list not matching the filter criteria
 * partition - This is filter and filterNot in one go. Returns a pair of two lists.
 * takeWhile - Returns all elements of the list till the index for which the function holds true. Example: For a list List(2, -4, 5, 7, 1), takeWhile(x => x > 0) returns List(2)
 * dropWhile - Returns all elements of the list after the index for which the function holds true. dropWhile for above would be List(-4, 5, 7, 1)
 * span - Combines results of takeWHile and dropWhile as a pair and returns it.
 */

def multiplyByFactor1(xs: List[Int], fc: Int): List[Int] = xs match {
  case Nil => xs
  case y :: ys => y * fc :: multiplyByFactor1(ys, fc)
}

def multiplyByFactor2(xs: List[Int], fc: Int): List[Int] = xs.map(x => x * fc)

print(multiplyByFactor2(List(1,2,3), 2))

def getPos(xs: List[Int]): List[Int] = xs.filter(x => x > 0)



/**
 * Lecture 5.5 - Reduction of Lists
 *    A common operation on lists is to combine the elements of a list using a given operator.
 *      sum(List(x1, ..., xn)) = 0 + x1 + x2 + ... + xn
 *      product(List(x1, ..., xn)) = 1 * x1 * x2 * ... * xn
 *
 *
 */


def sum[T](xs:List[T]): T = xs match {
  case Nil => 0
  case y::ys => y+sum(ys)
}
//def sum(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x + y)
def sum2(xs: List[Int]) = (0 :: xs).reduceLeft(_+_)
// this is the anther form of xs.foldLeft(0)(_+_)
def sum3(xs: List[Int]) = (xs foldLeft 0)(_ + _)

//(xs ++ ys) ++ zs = xs ++ (ys ++ zs)