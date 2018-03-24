


object LoanEvaluation {
  def main(args:Array[String]){
    
    println("Enter P for Permanent or T for Temporary Job Holder")
    var jobPosition = Console.readLine()
    if(jobPosition.equals("P"))
    {
      println("Enter M for married or Enter U for UnMarried")
      var maritalStatus= Console.readLine();
      if(maritalStatus.equals("M"))
      {
         println("Enter the Service Period")
         var serviceYear:Int = Console.readInt()
         if(serviceYear >=30)
         {
           println("Loan Amount you will get is 600000")
         }
         else
         {
           println("Loan Amount you will get is 350000")
         }
      }
      else
      {
        println("Enter the Service Period")
         var serviceYear:Int = Console.readInt()
         if(serviceYear >=30)
         {
           println("Loan Amount you will get is 500000")
         }
         else
         {
           println("Loan Amount you will get is 250000")
         }
      }
    }
      else{
        println("Loan Amount will be 100000")
      }
    }}