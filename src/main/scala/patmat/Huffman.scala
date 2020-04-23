package patmat

import scala.annotation.tailrec

/**
 *
 */


object Huffman {
  /**
   * A huffman code is represented by a binary tree.
   *
   * Every `Leaf` node of the tree represents one character of the alphabet that the tree can encode.
   * The weight of a `Leaf` is the frequency of appearance of the character.
   *
   * The branches of the huffman tree, the `Fork` nodes, represent a set containing all the characters
   * present in the leaves below it. The weight of a `Fork` node is the sum of the weights of these
   * leaves.
   */

  abstract class CodeTree

  case class Fork(left: CodeTree, right: CodeTree, chars:List[Char], weight:Int) extends CodeTree

  case class Leaf(char: Char, weight: Int) extends CodeTree

  // 1. Basic
  def weight(tree: CodeTree):Int = tree match {
    case Fork(_,_,_,w) => w
    case Leaf(_,w) => w
  }

  def chars(tree: CodeTree): List[Char] = tree  match {
    case Fork(_,_,chars,_) => chars
    case Leaf(c,_) => List(c)
  }

  def makeCodeTree(left: CodeTree, right:CodeTree):CodeTree = {
    Fork(left,right, this.chars(left) ::: this.chars(right), this.weight(left)+this.weight(right))
  }

  // 2. generate huffman trees
  def string2Chars(str:String):List[Char] = str.toList

  // get the number of chars appearing in the chars, return [(c, times) (c, times)]
  def times(chars:List[Char]):List[(Char, Int)] = chars.groupBy(identity).map{
    case (c, listC) => (c, listC.size)
  }.toList

  // sort the freq and convert tuple to Leaf instance
  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = {
    val sortedFreqs = freqs.sortBy(_._2)
    sortedFreqs.map{
      case (c,weight) => Leaf(c, weight)
    }
  }

  def singleton(trees: List[CodeTree]): Boolean = trees.size == 1

  def combineLogic(trees: List[CodeTree]): List[CodeTree] = {
    if (trees.size < 2)
      trees
    else {
      val fork = makeCodeTree(trees.head, trees.tail.head)
      val remaining = trees.tail.tail
      (fork :: remaining).sortBy(this.weight(_))
    }
  }

  def until(xxx: List[CodeTree] => Boolean, yyy: List[CodeTree] => List[CodeTree])(zzz: List[CodeTree]): CodeTree = {

    @tailrec
    def combine(lst: List[CodeTree]): CodeTree = {
      if (xxx(lst)) lst.head
      else combine(yyy(lst))
    }

    combine(zzz)
  }
  def createCodeTree(chars: List[Char]): CodeTree = {
    until(singleton, combineLogic)(makeOrderedLeafList(times(chars)))
  }


  // Part 3: Decoding

  type Bit = Int

  /**
   * This function decodes the bit sequence `bits` using the code tree `tree` and returns
   * the resulting list of characters.
   */
  def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {
    if (bits.isEmpty) Nil
    else {
      @tailrec
      def doSome(treeRemaining: CodeTree, bitsToDecode: List[Bit],
                 acc: List[Char]): List[Char] =
        (treeRemaining, bitsToDecode) match {
          case (Leaf(c, _), b) => doSome(tree, b, c :: acc)
          case (_, Nil) => acc
          case (Fork(l, _, _, _), x :: xs) if x == 0 => doSome(l, xs, acc)
          case (Fork(_, r, _, _), x :: xs) if x == 1 => doSome(r, xs, acc)
        }

      doSome(tree, bits, Nil).reverse
    }

  }


  /**
   * A Huffman coding tree for the French language.
   * Generated from the data given at
   * http://fr.wikipedia.org/wiki/Fr%C3%A9quence_d%27apparition_des_lettres_en_fran%C3%A7ais
   */
  val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s', 121895), Fork(Leaf('d', 56269), Fork(Fork(Fork(Leaf('x', 5928), Leaf('j', 8351), List('x', 'j'), 14279), Leaf('f', 16351), List('x', 'j', 'f'), 30630), Fork(Fork(Fork(Fork(Leaf('z', 2093), Fork(Leaf('k', 745), Leaf('w', 1747), List('k', 'w'), 2492), List('z', 'k', 'w'), 4585), Leaf('y', 4725), List('z', 'k', 'w', 'y'), 9310), Leaf('h', 11298), List('z', 'k', 'w', 'y', 'h'), 20608), Leaf('q', 20889), List('z', 'k', 'w', 'y', 'h', 'q'), 41497), List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127), List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291), Fork(Fork(Leaf('o', 82762), Leaf('l', 83668), List('o', 'l'), 166430), Fork(Fork(Leaf('m', 45521), Leaf('p', 46335), List('m', 'p'), 91856), Leaf('u', 96785), List('m', 'p', 'u'), 188641), List('o', 'l', 'm', 'p', 'u'), 355071), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362), Fork(Fork(Fork(Leaf('r', 100500), Fork(Leaf('c', 50003), Fork(Leaf('v', 24975), Fork(Leaf('g', 13288), Leaf('b', 13822), List('g', 'b'), 27110), List('v', 'g', 'b'), 52085), List('c', 'v', 'g', 'b'), 102088), List('r', 'c', 'v', 'g', 'b'), 202588), Fork(Leaf('n', 108812), Leaf('t', 111103), List('n', 't'), 219915), List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503), Fork(Leaf('e', 225947), Fork(Leaf('i', 115465), Leaf('a', 117110), List('i', 'a'), 232575), List('e', 'i', 'a'), 458522), List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)

  /**
   * What does the secret message say? Can you decode it?
   * For the decoding use the `frenchCode' Huffman tree defined above.
   **/
  val secret: List[Bit] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  /**
   * Write a function that returns the decoded secret
   */
  def decodedSecret: List[Char] = decode(frenchCode, secret)


  // Part 4a: Encoding using Huffman tree

  /**
   * This function encodes `text` using the code tree `tree`
   * into a sequence of bits.
   */
  def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {
    if (text.isEmpty) Nil
    else {

      def findLeafPath(c: Char, codeTree: CodeTree, acc: List[Bit]): List[Bit] = codeTree match {
        case Leaf(x, _) => if (x == c) acc else Nil
        case Fork(l, r, _, _) => findLeafPath(c, l, 0 :: acc) ::: findLeafPath(c, r, 1 :: acc)
      }

      text.flatMap(x => findLeafPath(x, tree, Nil).reverse)
    }
  }

  // Part 4b: Encoding using code table

  type CodeTable = List[(Char, List[Bit])]

  /**
   * This function returns the bit sequence that represents the character `char` in
   * the code table `table`.
   */
  def codeBits(table: CodeTable)(char: Char): List[Bit] = {
    table.find(_._1 == char).map(_._2).getOrElse(List.empty[Bit])
  }

  /**
   * Given a code tree, create a code table which contains, for every character in the
   * code tree, the sequence of bits representing that character.
   *
   * Hint: think of a recursive solution: every sub-tree of the code tree `tree` is itself
   * a valid code tree that can be represented as a code table. Using the code tables of the
   * sub-trees, think of how to build the code table for the entire tree.
   */

  def convert(tree: CodeTree): CodeTable = {

    def mkTable(remTree: CodeTree, currTable: CodeTable): CodeTable = remTree match {
      case Leaf(c, _) => (c, Nil) :: currTable
      case Fork(l, r, _, _) => mergeCodeTables(mkTable(l, currTable), mkTable(r, currTable))

    }

    mkTable(tree, Nil)
  }

  /**
   * This function takes two code tables and merges them into one. Depending on how you
   * use it in the `convert` method above, this merge method might also do some transformations
   * on the two parameter code tables.
   */
  def mergeCodeTables(a: CodeTable, b: CodeTable): CodeTable = {
    val leftTable = a.map {
      case (c, bitList) => (c, 0 :: bitList)
    }

    val rightTable = b map {
      case (c, bitList) => (c, 1 :: bitList)
    }

    leftTable ::: rightTable
  }

  /**
   * This function encodes `text` according to the code tree `tree`.
   *
   * To speed up the encoding process, it first converts the code tree to a code table
   * and then uses it to perform the actual encoding.
   */
  def quickEncode(tree: CodeTree)(text: List[Char]): List[Bit] = {
    val table = convert(tree)
    text.flatMap(codeBits(table)(_))
  }
















}

