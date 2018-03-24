import scala.collection.mutable.ListBuffer

// Program to find the sum and average of odd numbers
object OddNumbers {
  def main(args: Array[String]){ 
	println("Enter the starting range")
	var startingRange : Int = Console.readInt()
	println("Enter the ending range")
	var endingRange : Int = Console.readInt()
	var z = new ListBuffer[Int]()
	for(i <- startingRange to endingRange){
		if(i%2!=0)
		{
			println("Entered Number is Odd " +i)
			if(i%3==0)
			{
				println("Entered Number is Odd which is divisible by 3 " +i)
				z += i 
			}
		}
	}
	println("All elements are:")
    for ( x <- z ) {
		println( x )
    }
   
    var total = 0.0;
	for ( i <- 0 to (z.length - 1)) {
		total += z(i);
    }
    println("The sum of all odd numbers between 249 to 999 :  " + total);  
    println("The average of all odd numbers between 249 to 999 : " + total/(z.length));  
  }
}