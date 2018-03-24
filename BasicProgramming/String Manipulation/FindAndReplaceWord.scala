

object FindAndReplaceWord {
  def main(args: Array[String]){
    
    println("Enter a Sentence")
    var enteredWord: String=Console.readLine()
    
    println("Enter a Word to Find")
    var findWord: String=Console.readLine()
    
    if(enteredWord.contains(findWord)){
      
    println("Enter a Word to Replace that Word")
    var replaceWord: String=Console.readLine()
    
    if(findWord.length() == replaceWord.length()){
      
     
      
      println("The sentance after aletering is : " + enteredWord.replace(findWord, replaceWord))
    }
    else
    {
      println("Both words should be same in length")
    }  
    }
    else
      println("Entered Word is not Found in Sentence")
    
  
  }
}