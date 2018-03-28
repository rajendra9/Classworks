package bankProj

class BankAccount
{
  private[BankAccount] var accountNo : Int = 0
  private[BankAccount] var custName : String = "FalseData"
  private[BankAccount] var accBalance : Double = 0.0
  
  def getAccountNo() : Int = return accountNo
  def getCustName() : String = return custName
  def getAccBalance() : Double = return accBalance
  
  def setAccountNo(accountNo : Int) = this.accountNo = accountNo
  def setCustName(custName : String)  = this.custName = custName
  def setAccBalance(accBalance : Double) = this.accBalance = accBalance
  
  def BankAccount()
  {
    this.accountNo = 0
    this.custName = "FalseData"
    this.accBalance = 0.0
  }
  
  def this(accountNo : Int,custName : String,accBalance :Double)
  {
    this()
    this.accountNo = accountNo
    this.custName = custName
    this.accBalance = accBalance
  }
  
  override def toString() : String = return " AccountNo : "+accountNo +
                                            " CustomerName : "+custName+
                                            " AccountBalance : "+accBalance
  
}