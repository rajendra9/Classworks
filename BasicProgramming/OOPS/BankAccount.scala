//Creating a BankAccount class 
class BankAccount
{
  private var _accountNo=""
  private var _accountName=""
  
  //defining getters and setters.
  def accountNo=_accountNo
  def accountName=_accountName
  def accountNo_=(value:String)=_accountNo=value
  def accountName_=(value:String)=_accountName=value
  //var accountNo=""
  //var accountName=""
  var balance:Double=1000
  
  def this(accountNo:String,accountName:String,balance:Double){
    this()
    this.accountNo=accountNo
    this.accountName=accountName
    this.balance=balance
  }
  
  def this(accountNo:String,accountName:String){
    this()
    this.accountNo=accountNo
    this.accountName=accountName
  }
  
  override def toString="Account Number = "+this.accountNo+"\n"+
  "Account Name = "+this.accountName+"\n"+
  "Balance = "+this.balance
}

trait IBankServiceProvider{
  
  def getBalance(account:BankAccount):Double
  def checkAccount(accNo:String):BankAccount
  def depositMoney(account:BankAccount,amount:Double):Boolean
  def withdrawMoney(account:BankAccount,amount:Double):Boolean
  def transferMoney(fromAccount:BankAccount,toAccount:BankAccount,amount:Double):Boolean
}

class InsufficientFundException(s:String) extends Exception(s){}

class InvalidAmountException(s:String) extends Exception(s){}

class Bank(var lastAssignedNo:Int) extends IBankServiceProvider{
  @throws(classOf[InsufficientFundException])
  @throws(classOf[InvalidAmountException])
  var bankArr=new Array[BankAccount](50)
  var bacc=new BankAccount()
  override def getBalance(acc:BankAccount):Double={
    return acc.balance
  }
  
  override def checkAccount(accNo:String):BankAccount={
    for(i<- 0 to (bankArr.length-1)){
      if(bankArr(i).accountNo==accNo){
        return bacc
      }
    }
    return null
  }
  
  override def depositMoney(account:BankAccount,amount:Double):Boolean={
    if(amount<0){
      throw new InvalidAmountException("Invalid Amount")
    }
    account.balance+=amount
    return true
  }
  
  override def withdrawMoney(account:BankAccount,amount:Double):Boolean={
    if(amount>=account.balance){
      return false
    }
    else{
      account.balance-=amount
      return true
    }
  }
  
  override def transferMoney(fromAccount:BankAccount,toAccount:BankAccount,amount:Double):Boolean={
    if(amount>=fromAccount.balance){
      return false
    }
    else{
      fromAccount.balance-=amount
      toAccount.balance+=amount
      return true
    }
  }
  
}
object Bank{
  val lastAssignedNo=0
}

object BankAccount {
  def main(args:Array[String]){
    var b1=new BankAccount()
    b1.accountNo="21365"
    b1.accountName="Srinath Nair"
    b1.balance=1500
    var b2=new BankAccount("24587","Sainath Nair",5463)
    var b3=new BankAccount("32145","Amit Rai")
    var b=new Bank(0)
    b.bankArr(0)=b1
    b.bankArr(1)=b2
    b.bankArr(2)=b3
    
   
    //println(b.getBalance(b3))
    
    b.depositMoney(b2, -1000)
    if(true){
      println("Amount Deposited")
    }
    else{
      println("Sorry! We couldn't deposit the amount")
    }
    b.withdrawMoney(b1, 500)
    if(true){
      println("Amount Withdrwan")
    }
    else{
     throw new InsufficientFundException("Insufficient Balance")
    }
    b.transferMoney(b2, b1, 3000)
    println(b2.toString())
    println()
    println(b1.toString())
    /*println(b1.toString())
    println()
    println(b2.toString())
    println()
    println(b3.toString())*/
        
  }
}