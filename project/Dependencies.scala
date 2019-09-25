import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {
  
  val akka: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-persistence",
    "com.typesafe.akka" %% "akka-stream",
    "com.typesafe.akka" %% "akka-slf4j",
    
    // T Y P E D
    "com.typesafe.akka" %% "akka-actor-typed",
    "com.typesafe.akka" %% "akka-stream-typed",
    "com.typesafe.akka" %% "akka-persistence-typed"
  ).map(_ % Version.akka) ++ Seq(
    // L O G B A C K
    "ch.qos.logback" % "logback-classic" % Version.logback,
    "com.typesafe.akka" %% "akka-persistence-cassandra" % Version.akkaPersistenceCassandra,
    "com.github.dnvriend" %% "akka-persistence-jdbc" % Version.akkaPersistenceJdbc,
    "org.postgresql" % "postgresql" % Version.postgres
  )
  
  val akkaTest: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-testkit",
    "com.typesafe.akka" %% "akka-stream-testkit",
    
    // T Y P E D
    "com.typesafe.akka" %% "akka-actor-testkit-typed"
  ).map(_ % Version.akka) ++ Seq(
    "org.scalatest" %% "scalatest" % Version.scalaTest % "it,test",
    "com.typesafe.akka" %% "akka-stream-kafka-testkit" % Version.akkaKafka % "it,test",
    "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % Version.akkaPersistenceCassandra % Test,
    "com.github.dnvriend" %% "akka-persistence-inmemory" % Version.akkaPersistenceInmemory % Test
  )


  val todo: Seq[ModuleID]  = Seq(
    "com.typesafe.akka" %% "akka-persistence-typed" % Version.akka,
    "com.softwaremill.tapir" %% "tapir-core" % Version.tapir,
    "com.softwaremill.tapir" %% "tapir-akka-http-server" % Version.tapir,
    "com.softwaremill.tapir" %% "tapir-json-circe" % Version.tapir,
    "com.softwaremill.tapir" %% "tapir-openapi-docs" % Version.tapir,
    "com.softwaremill.tapir" %% "tapir-openapi-circe-yaml" % Version.tapir,
    "com.softwaremill.tapir" %% "tapir-swagger-ui-akka-http" % Version.tapir,
    //"org.iq80.leveldb" % "leveldb" % "0.12",
    "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
    "com.github.dnvriend" %% "akka-persistence-inmemory" % Version.akkaPersistenceInmemory,
    "ch.qos.logback" % "logback-classic" % Version.logback
  )

  val todoTest: Seq[ModuleID]  = Seq(
    "com.typesafe.akka" %% "akka-stream-testkit" % Version.akka,
    "com.typesafe.akka" %% "akka-http-testkit" % Version.akkaHttp
  )

  val cats: Seq[ModuleID] = Seq(
    // C A T S
    "org.typelevel" %% "cats-core",
    "org.typelevel" %% "cats-free"
  ).map(_ % Version.cats)

  val circe: Seq[ModuleID] = Seq(
    // C I R C E
    "io.circe" %% "circe-parser",
    "io.circe" %% "circe-generic"
  ).map(_ % Version.circe)


  val slick: Seq[ModuleID] = Seq(
    // S L I C K
    "com.lightbend.akka" %% "akka-stream-alpakka-slick" % Version.alpakkaSlick,
    
    // S L I C K
    "com.h2database" % "h2" % Version.h2
  )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % Version.scalaTest
  )

  object Version {
    val akka = "2.5.25"
    val akkaHttp = "10.1.10"
    val akkaKafka = "1.0.4"
    val alpakkaSlick = "1.1.0"
    val akkaPersistenceJdbc = "3.5.2"
    val akkaPersistenceCassandra = "0.99"
    val akkaPersistenceInmemory = "2.5.15.2"
    val scalaTest = "3.0.8"
    val postgres = "42.2.6"
    val cats = "2.0.0"
    val circe = "0.12.1"
    val logback = "1.2.3"
    val h2 = "1.4.199"
    val tapir = "0.10.2"
  }

}
