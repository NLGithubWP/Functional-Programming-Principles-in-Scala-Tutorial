trait HairColor

trait Iterator[A] {
  def hasNext: Boolean
  def next():A
}

class IntIterator(to: Int) extends Iterator[Int] {
  private var current=0

  override def hasNext: Boolean = current < to

  override def next(): Int = {
    if(hasNext) {
      val t = current
      current += 1
      t
    }
    else 0
  }
}

val intitear = new IntIterator(10)
intitear.next()
intitear.next()

import scala.collection.mutable.ArrayBuffer
trait Pet{val name: String}

class Dog(val name : String) extends Pet
class Cat(val name: String) extends Pet

val dog = new Dog("ww")
val cat  = new Cat("xixi")

//define a variable array, each element is typed of Pet
val animals = ArrayBuffer.empty[Pet]
animals.append(dog)
animals.append(cat)
animals.foreach(pet=>print(pet.name))

