

object Armstrong {
  def main(args:Array[String]){
    println("Enter a number = ")
    var number:Int=Console.readInt()
    var originalNumber:Int=number
    var sum:Int=0
    var number1:Int=0
    while(number>0){
      
      number1=number%10
      sum=sum+(number1*number1*number1)
      number=number/10
    }
    if(sum==originalNumber){
      println("\nThe number is Armstrong Number")
    }
    else{
      println("The number is not an Armstrong Number")
    }
  }
}