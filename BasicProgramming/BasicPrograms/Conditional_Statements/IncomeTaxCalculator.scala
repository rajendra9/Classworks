
// Program to calculate Income tax

object IncomeTaxCalculator {
  def main(args:Array[String]){
    println("Calculate Income Tax :-")
  println("Enter Annual Income = ")
  var annualIncome:Int=Console.readInt()
  println("Enter Investments in LIC = ")
  var licInvestments:Int=Console.readInt()
  if(licInvestments<=10000){
    annualIncome=annualIncome-licInvestments
  }
  else{
    annualIncome=annualIncome-10000
  }
  println("Enter Investments in Savings = ")
  var savingsInvestments:Int=Console.readInt()
  if(savingsInvestments<=60000){
    annualIncome=annualIncome-savingsInvestments
  }
  else{
    annualIncome=annualIncome-60000
  }
  println("Taxable Income = "+annualIncome)
  var tax:Int=0
  if(annualIncome>0&&annualIncome<=50000){
    tax=0
  }
  if(annualIncome>=50001&&annualIncome<=60000){
    tax=10
  }
  if(annualIncome>=60001&&annualIncome<=150000){
    tax=20
  }
  if(annualIncome>150000){
    tax=30
  }
  
  // Calculating the payable tax
  var payableTax:Int=annualIncome*tax/100
  println("Tax Payable = "+payableTax)
  }
  
  
}