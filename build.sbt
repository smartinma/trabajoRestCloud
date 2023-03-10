name := """trabajoFinalRestCloud"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += evolutions
libraryDependencies += jdbc
libraryDependencies += ehcache
libraryDependencies += "com.h2database" % "h2" % "1.4.200"
libraryDependencies += "org.fusesource.jansi" % "jansi" % "1.17.1"
libraryDependencies += "com.typesafe.play" %% "play-test" % "2.8.2" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "42.3.1"

maintainer := "mhernandezco.inf@upsa.es"








