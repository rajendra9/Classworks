package bankProj

trait IBankServices {
  
  def createAccount(custName : String,accBalance : Double): Boolean
  def deleteAccount(accNo : Int): Boolean
  def depositMoney(accNo : Int,depositAmt : Double): Boolean
  def withdrawMoney(accNo : Int,withdrawAmt : Double): Boolean
  def transferMoney(fromAccNo : Int,ToAccNo : Int,transferAmt : Double) :Boolean
  def displayAccount(accNo : Int) : String
  
}