object Loan {
  
 // Program to display the loan amount
 
  def main(args:Array[String])
  {
   println("job nature 1.permanent 2.temporary");
   var job:Int=0;
   job=Console.readInt();
   if(job==1)
   {
     println("marital stauts 1.married 2.unmarried");
     var marriage:Int=Console.readInt();
     if(marriage==1)
     {
       println("experience 1.less than 30 ys 2.more than 30 yrs");
     var exp=Console.readInt();
     if(exp==1)
       println("your eligible loan amount is:rs.350,000");
     else
       println("our loan amount is  rs.600,000");
   }
     else
     {
        println("experience 1.less than 30 ys 2.more than 30 yrs");
     var exp1:Int=Console.readInt();
     if(exp1==1)
       println("your eligible loan amount is:rs.250,000");
     else
       println("our loan amount is  rs.500,000");
     }
    
 
  }
   else
      println("our loan amount is  rs.100,000");
}
}