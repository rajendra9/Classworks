package bankProj
import java.io.FileWriter
import java.util
import java.util.Calendar
import java.util.Formatter.DateTime


object BankMain2 {
  
  def main(args : Array[String]){
  var obj = new Bank();
  
  try
  {
    obj.createAccount("Radha", 100.00)
    obj.createAccount("rk",200.00)
    println(obj.displayAccount(1001))
    println(obj.displayAccount(1002))
                                       // println(obj.displayAccount(1003))
   
    
    obj.depositMoney(1001, 200.00)
    println(obj.displayAccount(1001))
                                       //obj.withdrawMoney(1001, 500.00)
   obj.transferMoney(1001, 1002, 100.00)
                                        // obj.transferMoney(1005, 1002, 100.00)
                                        //obj.transferMoney(1001, 1005, 100.00)
                                        // obj.transferMoney(1001, 1002, 500.00)
    println(obj.displayAccount(1001))
    println(obj.displayAccount(1002))
    
    println(obj.deleteAccount(1002))
                                        println(obj.deleteAccount(1003))
  }
  catch
  {
    case e:Exception=>println("Exception Occured" +e.printStackTrace())
 
 //Creating data to enter in a log file.  
             var logMessage="Date : "+Calendar.getInstance().getTime+", Exception Occured : "+e.getClass
             var start="-------------------------------------------------------"
             var date="Date : "+Calendar.getInstance().getTime
             var exceptionType="Exception Occured : "+e.getClass
             var exceptionContent="Exception Description : "+e.getMessage
 
             //Creating an object to pass data to log file.
             val writer:FileWriter = new FileWriter(new java.io.File("D:/mohamedashfaq/Log.txt"),true)
             writer.append(start)
             writer.append(String.format("%n"))
             writer.append(date)
             writer.append(String.format("%n"))
             writer.append(exceptionType)
             writer.append(String.format("%n"))
             writer.append(exceptionContent)
             writer.append(String.format("%n%n"))
             writer.close()
             println("Exceptions successfully logged into file.")
  
  }
  }
}