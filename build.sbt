name := "test"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18"
)

//5.1.38 is the version Beth has, but 5.1.18 is the one that doesn't error


play.Project.playJavaSettings
