name := "Bezier Curve"

version := "1.0"

mainClass := Some("bezier.MainWindow")

scalaSource in Compile <<= baseDirectory(_ / "src")

scalacOptions ++= "-deprecation -feature -language:implicitConversions".split(" ").toSeq
