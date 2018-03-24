

// Program to sort the given array
object ArraySorting {
  def main(args: Array[String]){
	var z = new Array[Int](5)
    println("Enter the Elements to store in Array")
    for( x <- 0 to z.length-1){
		z(x)=Console.readInt()
    }
	println("The elements in array are: ")
		for(x <- 0 to z.length-1){
			println(""+z(x))
		}    
		
		// To sort the array
		var  pos = z.sorted
		println("Element after sorting are")
		for(x <- 0 to pos.length-1){
			println(" "+pos(x))
		}  
	}
}