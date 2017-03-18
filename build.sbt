enablePlugins(OrnatePlugin)

name := "ScalaKitchen"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.novocode" %% "ornate" % "0.4",
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.json4s" %% "json4s-native" % "3.5.0",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5"
)

(dependencyClasspath in Ornate) := (fullClasspath in Runtime).value ++ (dependencyClasspath in Ornate).value
