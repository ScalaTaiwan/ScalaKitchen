package a {
  class A (val n: Int)
}

package object a {
  implicit def aToString(a: A): String = s"A: ${a.n}"
}

object Test {
    import a._
    def main(s: Array[String]) = {
      val as: String = new A(100)
      println(as)
    }
}