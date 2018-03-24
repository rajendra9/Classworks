

object eb_bill {
  
  def main(args: Array[String])
  {
	// Declaring the unit and amount using multiple assignments
    var(unit:Int,amt:Int)=Pair(0,0);
    println("Enter the amount of unit:");
    unit=Console.readInt();
    if(unit>=1000)
    {
      amt=unit*10;
      println("the amount that need to be paid is:",amt);
    }
    else if(unit>=500)
    {
      amt=unit*5;
      println("the amount that need to ba paid is:",amt);
    }
    else if(unit>=200)
    {
      amt=unit*2;
      println("the amount that need to ba paid is:",amt);
    }
    else
    {
      amt=unit;
      println("the amount that need to ba paid is:",amt);
    }
  }
}