

// Program to find the sum of the given array
object ArraySum {
  def main(args:Array[String]){
    var arrayOfNumbers=new Array[Int](5)
      
    println("Enter elements of the array :- \n")
    for(i<-0 to arrayOfNumbers.length-1){
      arrayOfNumbers(i)=Console.readInt()
    }
    var sum=0
    for(i<-0 to arrayOfNumbers.length-1){
      sum=sum+arrayOfNumbers(i)
    }
    println("The Sum of elements in the array is = "+sum)
  }
}