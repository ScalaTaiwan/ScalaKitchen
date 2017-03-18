package scalataiwan.ornatescalafiddle

import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.native.JsonMethods._

import scalaj.http.Http

object Libraries extends App {
  lazy val libraries: Map[String, JObject] = {
    val content = Http("https://scalafiddle.io/")
      .timeout(10000, 30000)
      .asString
      .body
    val data = """window\.ScalaFiddleData = (.+);""".r.findFirstMatchIn(content).get.group(1)
    implicit val formats = DefaultFormats
    val ls = (parse(data) \ "available").extract[List[JObject]]
    Map(ls.map(l => ((l \ "name").extract[String] + "-" +  (l \ "version").extract[String]) -> l): _*)
  }
  def getLibrary(name: String): Option[JObject] = libraries.get(name)
  println(libraries.keys)
  println(getLibrary("Java8Time-0.1.0"))
}
