// T <: A T is the sub class of A

abstract class Animal{
  def name: String
}

abstract  class Pet extends Animal {}

class Cat extends Pet {
  override def name: String = "Cat"
}

class Dog extends Pet {
  override def name: String = "Dog"
}

class Lion extends Animal {
  override def name: String = "Lion"
}

class PetContainer[P <: Pet](p: P) {
  def pet : P = p
}

val t1 =  new PetContainer[Dog](new Dog)
val t2 =  new PetContainer[Cat](new Cat)


