

var a: List[String] = List()
a = a.appended("123")
a = a.appended("123")
a = "3" :: a
print(a)
print(a.head)
print(a.tail)

class Stack[A] {
  private var elements: List[A] = Nil
  def push(x: A) {elements = x :: elements}
  def peek: A = elements.head
  def pop():A = {
    val currntTop = peek
    elements = elements.tail
    currntTop
  }
}

val stack  = new Stack[Int]
stack.push(1)
stack.push(2)
print(stack.pop())
print(stack.pop())

class Fruit
class Apple extends Fruit
class Banana extends Fruit

val stack2 = new Stack[Fruit]
val apple = new Apple
val banana = new Banana

stack2.push(apple)
stack2.push(banana)

