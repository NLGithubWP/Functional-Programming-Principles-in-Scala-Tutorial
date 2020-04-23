//use to replace if/else

import scala.util.Random
// x is random number between 0 to 10
val x: Int = Random.nextInt(10)

x match {
  case 0 => "zero"
  case 1 => "one"
  case _ => "other"
}


def matchtest(x:Int):String=x match{
  case 1 => "one"
  case _ => "other"
}

matchtest(3)
matchtest(1)

// case class is suitable for pattern match
abstract class Notification

case class Email(sender:String, body:String) extends Notification
case class SMS(sender:String, body:String) extends Notification
case class VoiceMessage(sender:String, body:String) extends Notification


def showNotifications(notification: Notification):String={
  notification match{
    case Email(sender, body) if (1!=1) => "email"
    case SMS(sender, body) => "SMS"
    case VoiceMessage(sender, body) => "VoiceMessage"
    case _ => "nothing found"

  }
}

val testcase = Email(sender = "me", body = "nothing")
val  res = showNotifications(testcase)
print(res)

// use sealed to make sure each class are at the same file

sealed abstract class Furniture
case class Couch() extends Furniture
case class Chair() extends Furniture
def findPlacetoSit(piece: Furniture): String = {
  piece match {
    case a: Couch => "lie on it"
    case b: Couch => "sit on it"
  }
}















