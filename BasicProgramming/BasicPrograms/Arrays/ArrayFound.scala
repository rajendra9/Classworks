import scala.collection.mutable.ListBuffer
import scala.util.control._

// Program to seperate the elements by comparing two arrays
object ArrayFound {
  def main(args: Array[String]){
        var original = new Array[Int](5)
        var key = new Array[Int](5)
        var isTrue : Boolean = false
        var foundList = new ListBuffer[Int]()
        var notfoundList = new ListBuffer[Int]()
		println("Enter the Elements to store in Original Array")
		for( x <- 0 to original.length-1){
			original(x)=Console.readInt()
		}
        println("Enter the Elements to store in Key Array")
		for( x <- 0 to key.length-1){
			key(x)=Console.readInt()
		}
        
		for ( i <- 0 to original.length-1 ) {
			for(j <- 0 to key.length-1){
				if(original(i)==key(j)){
                    foundList += original(i)
                    Breaks;
				}
			}  
		}
  
		for(i <- 0 to original.length-1 ){
			if(!foundList.contains(original(i))){
				notfoundList += original(i)
			}  
		}
		// Traversing the list buffer using the object
		println("Found Array")
		for ( x <- foundList ) {
			println( x )
		}
		println("Not Found Array")
		for ( x <- notfoundList ) {
			println( x )
		}
	}
}