

// Program to find the minumum element in the array
object PrintArray {
  def main(args:Array[String]){
    var arrayOfNumbers=new Array[Int](5)
    println("Enter the elements \n")
    for(i<-0 to (arrayOfNumbers.length-1)){
      arrayOfNumbers(i)=Console.readInt()
    }
    var min:Int=arrayOfNumbers(0)
    for(i<-1 to arrayOfNumbers.length-1){
      if(arrayOfNumbers(i)<min){
        min=arrayOfNumbers(i)
      }
    }
    println("The minimum element is = "+min)
  }
}