package bankProj


object BankMain {
  
  def main(args : Array[String]){
  var obj = new Bank();
 
 
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
    
     obj.deleteAccount(1001)
     obj.displayAccount(1001)
                                        //println(obj.deleteAccount(1003))
 
  }
}