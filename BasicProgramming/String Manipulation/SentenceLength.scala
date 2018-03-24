

object SentenceLength {
  def main(args: Array[String]) {
    println("Enter a sentance")
    var enteredSentance : String = Console.readLine()
    
    println("Length of the second word in entered sentence :" +wordLength(enteredSentance))
  }
  
  def wordLength( sentence : String )  : Int= {
   var words = sentence.split(" ")
    var secodnWords = words(1)
    return secodnWords.length()
  }
  
}