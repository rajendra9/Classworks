/*Program to create an original array and key array, and then find the count of each key array element in
original array.*/

object ElementCount 
{
  def main(args:Array[String])
  {
    //Declaring the original array and key array.
    var original=Array[Int](12,54,19,20,44,19,14,54,20,19)
    var key=Array[Int](12,54,44,20,19)
    
    //Declare output array
    var output=new Array[Int](key.length)
    
    var count:Int=0
    
    for(i <- 0 to key.length-1)
    {
      //Count the frequency of occurence of key element in original array.
      for(j <- 0 to original.length-1)
      {
        if(key(i)==original(j))
        {
         count=count+1
         
         //store the count of frequency of key element at the i location of output array
         output(i)=output(i)+1
        }

      }
    }
    
    println("The following elements are :- \n")
    for(i <- 0 to output.length-1)
    {
      print(" "+output(i))
    }
  }
}