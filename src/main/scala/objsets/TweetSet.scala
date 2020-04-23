package objsets
import TweetReader._

class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
      "Text: " + text + " [" + retweets + "]"
}

abstract class TweetSet {
  def descendingByRetweet: TweetList = {
    def getList(set: TweetSet): TweetList = {
      if (set.isEmpty) Nil
      else {
        val tweet = set.mostRetweeted
        val setMinusTweet = set.remove(tweet)
        new Cons(tweet, getList(setMinusTweet))
      }
    }

    getList(this)
  }
  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, new Empty)
  def isEmpty: Boolean
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet
  def union(that: TweetSet): TweetSet
  def mostRetweeted: Tweet
  def incl(tweet: Tweet): TweetSet
  def remove(tweet: Tweet): TweetSet
  def contains(tweet: Tweet): Boolean
  def foreach(f: Tweet => Unit): Unit
}

class Empty extends TweetSet {
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc
  override def isEmpty: Boolean = true
  override def mostRetweeted: Tweet = throw new NoSuchElementException("Empty.mostRetweeted")
  override def union(that: TweetSet): TweetSet = that
  def contains(tweet: Tweet): Boolean = false
  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)
  def remove(tweet: Tweet): TweetSet = this
  def foreach(f: Tweet => Unit): Unit = ()
}

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {
  override def mostRetweeted: Tweet = {
    val all = right.union(left)
    val arepopular = all.filter(tw => tw.retweets > elem.retweets)
    if (arepopular.isEmpty) elem else arepopular.mostRetweeted
  }

  override def isEmpty: Boolean = false

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    val elemAcc = if (p(elem)) acc.incl(elem) else acc
    val leftAcc = left.filterAcc(p, elemAcc)
    right.filterAcc(p, leftAcc)
  }

  override def union(that: TweetSet): TweetSet = {
    (left.union(right.union(that))).incl(elem)
  }

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }
}

trait TweetList {
  def head: Tweet

  def tail: TweetList

  def isEmpty: Boolean

  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")

  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")

  def isEmpty = true
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}


object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  lazy val googleTweets: TweetSet = {
    Option
    allTweets.filter(tw => google.exists(tw.text.contains(_)))
  }

  lazy val appleTweets: TweetSet = {
    allTweets.filter(tw => apple.exists(tw.text.contains(_)))
  }
  lazy val trending: TweetList = (googleTweets.union(appleTweets)).descendingByRetweet
}

