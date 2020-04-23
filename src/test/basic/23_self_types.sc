trait User{
  def username: String
}

trait Tweeter {
  // 注意这一行
  // 这一行将 this 的类型指定为 User
  // 此时这个 trait 相当于变成了 User 的子特征，可以使用 User 中的元素了
  this: User =>
  def tweet(tweetText: String) = print(s"$username: $tweetText")
}

class VerifiedTweeter(username_ : String) extends Tweeter with User {
  def username = s"real $username_"
}

val realBeyonce = new VerifiedTweeter("Beyonce")
realBeyonce.tweet("just spilled my glass of lemonade")

