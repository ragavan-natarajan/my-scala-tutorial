name := "Binary Tree Serde"

version := "0.1"

assemblyJarName in assembly := "binary-tree-serde.jar"

libraryDependencies += "junit" % "junit" % "4.10" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"

mainClass in assembly := Some("online.ragavan.example.binarytreeserialization.TreeReconstructor")
