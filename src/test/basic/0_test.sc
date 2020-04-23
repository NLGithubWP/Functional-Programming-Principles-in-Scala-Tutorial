import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

var test = "adf".toList


for (i <- List(1,2,3)){
  print(test.head)
  test = test.tail
}
print(test)


var stack = ListBuffer[Char]()


stack.append('c')
