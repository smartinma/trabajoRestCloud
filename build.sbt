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



