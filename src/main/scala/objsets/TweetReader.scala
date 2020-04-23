package objsets

import scala.annotation.tailrec
import scala.util.parsing.json.JSON

object TweetReader {

  object ParseTweets {

    def getList[T](s:String): List[T] ={
      JSON.parseFull(s).get.asInstanceOf[List[T]]
    }

    def getMap(s:String): Map[String, Any] = {
      JSON.parseFull(s).get.asInstanceOf[Map[String, Any]]
    }

    def getTweets(user: String, json: String): List[Tweet] = {

      for (map <- this.getList[Map[String, Any]](json))
        yield {
          val text = map("text")
          val retweets = map("retweet_count")
          new Tweet(user, text.toString, retweets.toString.toDouble.toInt)
        }
    }
    def getTweetData(user: String, json: String): List[Tweet]={
      val l = this.getList[Map[String, Any]](json)
      for (map <- l)
        yield {
          val text = map("text")
          val retweets = map("retweet_count")
          new Tweet(user, text.toString, retweets.toString.toDouble.toInt)
        }
    }
  }
  // foldleft take 3 parameters, type, variable and function taking 2 params -- variable and element which is element of the given list
  // foldLeft[T](t:T)(op: (T, A) => T)
  // below method generate a Empty instance which call.incl method to process each element of the l list
  def toTweetSet(l: List[Tweet]): TweetSet = {
    l.foldLeft[TweetSet](new Empty: TweetSet)(_.incl(_))
  }

  def unparserToData(tws: List[Tweet]): String={
    val buf  = new StringBuilder
    for (tw <- tws) {
      val json = "{ \"user\": \"" + tw.user + "\", \"text\": \"" +
        tw.text.replaceAll(""""""", "\\\\\\\"") + "\", \"retweets\": " +
        tw.retweets + ".0 }"
      buf.append(json + ",\n")
    }
    buf.toString
  }

  val sites = List("gizmodo", "TechCrunch", "engadget", "amazondeals", "CNET", "gadgetlab", "mashable")

  private val gizmodoTweets = this.ParseTweets.getTweetData("gizmodo", TweetData.gizmodo)
  private val techCrunchTweets = this.ParseTweets.getTweetData("TechCrunch", TweetData.TechCrunch)
  private val engadgetTweets = this.ParseTweets.getTweetData("engadget", TweetData.engadget)
  private val amazondealsTweets = this.ParseTweets.getTweetData("amazondeals", TweetData.amazondeals)
  private val cnetTweets = this.ParseTweets.getTweetData("CNET", TweetData.CNET)
  private val gadgetlabTweets = this.ParseTweets.getTweetData("gadgetlab", TweetData.gadgetlab)
  private val mashableTweets = this.ParseTweets.getTweetData("mashable", TweetData.mashable)

  private val sources = List(gizmodoTweets, techCrunchTweets, engadgetTweets, amazondealsTweets, cnetTweets, gadgetlabTweets, mashableTweets)

  val tweetMap: Map[String, List[Tweet]] =
    Map() ++ Seq((sites.head -> gizmodoTweets),
                 (sites(1) -> techCrunchTweets),
                 (sites(2) -> engadgetTweets),
                 (sites(3) -> amazondealsTweets),
                 (sites(4) -> cnetTweets),
                 (sites(5) -> gadgetlabTweets),
                 (sites(6) -> mashableTweets))

  val tweetSets: List[TweetSet] = sources.map(tweetslist => toTweetSet(tweetslist))

  private val siteTweetSetMap: Map[String, TweetSet] =
    Map() ++ (sites zip tweetSets)

  @tailrec
  private def unionOfAllTweetSets(curSets: List[TweetSet], acc: TweetSet): TweetSet =
    if (curSets.isEmpty) acc
    else unionOfAllTweetSets(curSets.tail, acc.union(curSets.head))

  val allTweets: TweetSet = unionOfAllTweetSets(tweetSets, new Empty)

}
