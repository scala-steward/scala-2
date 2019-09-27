lazy val commonSettings = Settings.value ++ Seq(
  organization := "io.github.mvillafuertem",
  version := "0.1",
  scalaVersion := "2.13.1",
  homepage := Some(url("https://github.com/mvillafuertem/scala")),
  licenses := List("MIT" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "mvillafuertem",
      "Miguel Villafuerte",
      "mvillafuertem@email.com",
      url("https://github.com/mvillafuertem")
    )
  )
)

lazy val scala = (project in file("."))
  .aggregate(
    advanced,
    akka,
    algorithms,
    cats,
    slick,
    todo
  )
  // S E T T I N G S
  .settings(commonSettings)
  .settings(Settings.noPublish)

lazy val advanced = (project in file("advanced"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.test)

lazy val akka = (project in file("akka"))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(NexusSettings.value)
  .settings(libraryDependencies ++= Dependencies.akka)
  .settings(libraryDependencies ++= Dependencies.akkaTest)
  .settings(libraryDependencies ++= Dependencies.test)

lazy val todo = (project in file("applications/todo"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(BuildInfoSettings.value)
  .settings(libraryDependencies ++= Dependencies.todo)
  .settings(libraryDependencies ++= Dependencies.todoTest)
  .settings(libraryDependencies ++= Dependencies.test)
  // P L U G I N S
  .enablePlugins(BuildInfoPlugin)
  .enablePlugins(GitVersioning)

lazy val algorithms = (project in file("algorithms"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.test)

lazy val cats = (project in file("cats"))
  .dependsOn(algorithms)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.cats)
  .settings(libraryDependencies ++= Dependencies.test)

lazy val circe = (project in file("circe"))
  .dependsOn(algorithms)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.circe)
  .settings(libraryDependencies ++= Dependencies.test)

lazy val slick = (project in file("slick"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.slick)
  .settings(libraryDependencies ++= Dependencies.test)

lazy val zio = (project in file("zio"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.zio)
  .settings(libraryDependencies ++= Dependencies.test)