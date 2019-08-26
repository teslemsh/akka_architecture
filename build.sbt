name := "cellular-network-processing"

version := "1.0"

scalaVersion := "2.12.8"

lazy val Versions = new {
  val phantom = "2.39.0"
  val util = "0.50.0"
  val scalatest = "3.0.5"
  val scala = "2.12.8"
  val akkaVersion = "2.6.0-M2"
  val playJson = "2.7.3"
  val liftJson = "3.3.0"
  
}
resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.typesafeRepo("releases"),
  Resolver.bintrayRepo("websudos", "oss-releases")
)

libraryDependencies ++= Seq(
  "com.outworkers"  %%  "phantom-dsl"       % Versions.phantom,
  "com.outworkers"  %%  "phantom-streams"   % Versions.phantom,
  "com.outworkers"  %%  "util-testing"      % Versions.util % Test,
  "org.scalatest"   %%  "scalatest"         % Versions.scalatest % Test,
  "org.scala-lang"  % "scala-reflect"        % Versions.scala,
  "com.typesafe.akka" %% "akka-actor"       % Versions.akkaVersion,
  "com.typesafe.akka" %% "akka-testkit"     % Versions.akkaVersion,
  "com.typesafe.play" %% "play-json"        % Versions.playJson,
  "net.liftweb" %% "lift-json"              % Versions.liftJson
)