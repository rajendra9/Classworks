package bankProj
import scala.collection.mutable.Map;

class Bank extends IBankServices
{
   private [Bank] var bankAccounts : Map[Int,BankAccount] = Map()
   private [Bank] var accNoGenerator : Int = 1001
   private var result : Boolean = false
   private var resultString : String = null
   
   def createAccount(custName : String,accBalance : Double) : Boolean =
   {
     try{
          result = false
          var newAccount = new BankAccount(accNoGenerator,custName,accBalance)
          if(bankAccounts.contains(accNoGenerator) == false)
             {
                bankAccounts.put(accNoGenerator, newAccount)
                accNoGenerator += 1
          
                result = true
             }
         else
                throw new DuplicateAccountException()
     }
     catch{
           case e:Exception => println(BankLogger.StatusLogger(accNoGenerator +" Account Creation",e))
           
     }
     println(BankLogger.StatusLogger(accNoGenerator - 1+" Account Creation",result))
     return result
   }
   
    def deleteAccount(accountNo : Int) : Boolean =
     {
      try{
           result = false
           if(bankAccounts.contains(accountNo))
             {
               bankAccounts.remove(accountNo)
                  result = true
             }
           else
                throw new InvalidAccountException()
       }
       catch{
             case e:Exception => println(BankLogger.StatusLogger(accountNo + " Account Deletion",e))
     }
       println(BankLogger.StatusLogger(accountNo + " Account Deletion",result))
      return result
   }
   
  def depositMoney(accountNo : Int,depositAmt : Double): Boolean =
  {
    try{
          result = false
          if(bankAccounts.contains(accountNo))
             {
              if(depositAmt > 0) 
                {
                 bankAccounts.get(accountNo).get.setAccBalance(bankAccounts.get(accountNo).get.getAccBalance()+depositAmt)  
       
                 result = true
                }
              else
                 throw new InvalidAmountException()
             }
            else
                 throw new InvalidAccountException()
        }
     catch{
             case e:Exception => println(BankLogger.StatusLogger(accountNo + " DepositMoney",e))
     }
     println(BankLogger.StatusLogger(accountNo + " DepositMoney",result))
    return result
    
  }
  
  def withdrawMoney(accountNo : Int,withdrawAmt : Double): Boolean =
  {
    try{
         result = false
          if(bankAccounts.contains(accountNo))
           {
              if(bankAccounts.get(accountNo).get.getAccBalance() >= withdrawAmt )
              {
                if(withdrawAmt > 0)
                  {
                   bankAccounts.get(accountNo).get.setAccBalance(bankAccounts.get(accountNo).get.getAccBalance() - withdrawAmt)
                   result = true
                  }
                else
                     throw new InvalidAmountException()
              }
              else
                 throw new InsufficientAmountException() 
            }
          else
              throw new InvalidAccountException()
     }
     catch{
             case e:Exception => println(BankLogger.StatusLogger(accountNo + " WithdrawMoney" ,e))
     }
     println(BankLogger.StatusLogger(accountNo + " WithdrawMoney" ,result))
    return result
    
  }
   
  def transferMoney(fromAccNo : Int,toAccNo : Int,transferAmt : Double) :Boolean =
  {
    try{
       result = false
   
    if(bankAccounts.contains(fromAccNo) )
    {
      
      if(bankAccounts.contains(toAccNo))
      {
        if(transferAmt > 0)
        {
         if(bankAccounts.get(fromAccNo).get.getAccBalance() >= transferAmt)
           {
            bankAccounts.get(fromAccNo).get.setAccBalance(bankAccounts.get(fromAccNo).get.getAccBalance() - transferAmt)
            bankAccounts.get(toAccNo).get.setAccBalance(bankAccounts.get(toAccNo).get.getAccBalance() + transferAmt)
            result = true
           }
         else
           throw new InsufficientAmountException()
        }else
            throw new InvalidAmountException()
      }
      else
        throw new InvalidAccountException()
    }
    else
       throw new InvalidAccountException()
     }
     catch{
       case e:Exception => println(BankLogger.StatusLogger(fromAccNo + " MoneyTransfer" ,e))
     }
     println(BankLogger.StatusLogger(fromAccNo + " MoneyTransfer" ,result))
    return result
  }
  
   def displayAccount(accountNo : Int) : String =
   {
     try{
        resultString = null
     if(bankAccounts.contains(accountNo))
     {
     resultString = bankAccounts.get(accountNo).toString()
     }
     else
        throw new InvalidAccountException()
     }
     catch{
       case e:Exception => println(BankLogger.StatusLogger("Display",e))
     }
     
      
     return resultString
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}