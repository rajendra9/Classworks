
import scala.deprecated
object depricate {
 
@deprecated def printMessage()=
{
  println("this message is deprecated")
}
 def main(args:Array[String])
 {
  printMessage()
 }
}