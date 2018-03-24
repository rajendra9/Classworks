import scala.util.control._


object PrimeNumbers {
  def main(args: Array[String]){
   println("Enter the starting range")
   var startingRange : Int = Console.readInt()
   println("Enter the ending range")
   var endingRange : Int = Console.readInt()
     var loop = new Breaks
     for(r <- startingRange to endingRange){
        var flag:Int = 0
       for(t<- 2 to r/2){
      
      if(r%t==0){
        flag=1
        
      }
    }
    if(flag==0){
      println("Prime number between the entered range :" +r)
    }  
     }  
}
}