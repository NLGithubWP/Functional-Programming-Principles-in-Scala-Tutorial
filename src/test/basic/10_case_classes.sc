//case class is suitable for constant data,
//similar to named-tuple in python

case class Book(isbn: String)

//no need to use new, since there is a default apply method
val a = Book("123")

case class Message(sender: String, recipient: String,  body: String)
val b = Message("123", "456", "789")
print(b.body)

val c1 = Message("1", "2", "3")
val c2 = Message("1", "2", "3")

// compare by value of variables
val test_same = c1==c2

print(c1.body)