

// Program to print the electricity bill
object ElectricityBill {
  def main(args:Array[String]){
    println("Enter the number of units = ")
    var units:Int=Console.readInt()
    var bill:Int=0
    if(units<=200){
      bill=(units*1)
    }
    else if(units>200 && units<=500){
      bill=(units*2)
    }
    else if(units>500 && units<=1000){
      bill=(units*5)
    }
    else{
      bill=(units*10)
    }
    println("The Bill is = "+bill)
  }
}