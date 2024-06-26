package io.github.mvillafuertem.foundations.action.imperative

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

trait Console {

  def writeLine(message: String): Unit

  def readLine(): String

}

object Console {

  val system: Console = new Console {
    override def writeLine(message: String): Unit = println(message)

    override def readLine(): String = StdIn.readLine()
  }

  def mock(inputs: ListBuffer[String], outputs: ListBuffer[String]): Console = new Console {
    override def writeLine(message: String): Unit = outputs.append(message)

    override def readLine(): String =
      if (inputs.isEmpty) throw new Exception("No input in the console")
      else inputs.remove(0)
  }

}
