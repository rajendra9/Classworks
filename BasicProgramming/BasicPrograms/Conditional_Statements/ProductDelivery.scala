
// Program to decide the mode of transportation

object ProductDelivery {
  def main(args:Array[String]){
    println("Enter the Delivery details :- \n")
    println("Enter y if priority is urgent and n if not = ")
    var priority:String=Console.readLine()
    println("\nEnter weight(Kg.) = ")
    var weight:Float=Console.readFloat()
    println("\nEnter Distance(Km.) = ")
    var distance:Float=Console.readFloat()
    var modeOfTransport:String=""
    if(priority=="n"){
      if(weight<=5){
        modeOfTransport="post"
      }
      else{
        if(weight>5){
          if(distance<=250){
            modeOfTransport="lorry"
          }
          else{
            modeOfTransport="train"
          }
        }
      }
    }
    else{
      if(weight<100){
        if(distance<50){
          modeOfTransport="van"
        }
        else{
          modeOfTransport="train"
        }
      }
      else{
        modeOfTransport="train"
      }
    }
    println("\nThe mode of transport = "+modeOfTransport)
  }
}