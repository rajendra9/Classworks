package com.htc
import java.util
import java.util.Calendar
import java.util.Formatter.DateTime
import java.io.FileWriter
import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger
import java.util.logging.Handler
import java.util.logging.FileHandler
import java.util.logging.ConsoleHandler


object Employee  
{
  val log:Logger=Logger.getLogger("\n"+Employee.getClass().toString())
  def main(args: Array[String])
  {
    //initializing EmployeeEntity and EmployeeDAO objects
      var employeeObj = new EmployeeDAO()
      var empObj=new EmployeeEntity()
      
      var e1=new EmployeeEntity()
      var e2=new EmployeeEntity(123,"Anil Nair",13364.0)
      
      e1.empId_=(12)
      e1.empName_=("Anil Nair")
      e1.salary_=(13364.0)
      
      println("Implementing toString() :- \n")
      println(e1.toString())             //prints empId,empName and salary 
      
      println("\nImplementing hashCode() :- \n")
      println(e1.hashCode())             //prints hashCode of empName
      
      println("\nImplementing equals() :- \n")
      if(e1.equals(e2)){
        println("Objects are equal")
      }
      else{
        println("Objects are not equal")
      }
      
      println("\n1.Insert Employee Records")
      println("2.Get Employee Records")
      println("3.Update Employee Records")
      println("4.Delete Employee Records")
   
      println("\nEnter the mode of operation")
      var mode=Console.readInt()
      log.info("com.htc.Employee")
      log.warning("This program can cause EmployeeNotFoundException,InvalidSalaryException")
       try
       {
         var fileHandler=new FileHandler("./src/Loggers.log")
         log.addHandler(fileHandler)
         fileHandler.setLevel(Level.ALL)
         log.setLevel(Level.ALL)
         log.config("Configuration done")
         
         if(mode==1)
         {
           println("\nEnter Employee Name")
           var ename=Console.readLine()
           println("\nEnter Salary")
           var sal=Console.readDouble()
           empObj.empName_=(ename)
           empObj.salary_=(sal)
           if(employeeObj.insertEmployeeData(empObj))
           {
              println("Records Inserted")
           }
         }
         else if(mode==2)
         {
           println("Enter the employee ID to display details")
           var eId : Int = Console.readInt()
           println(employeeObj.findEmployeeData(eId))
         }
         else if(mode==3)
         {
           println("Enter Employee ID = ")
            var eId=Console.readInt()
            println()
            println("Enter New Salary value = ")
            var esal=Console.readDouble()
            if(employeeObj.updateEmployeeData(eId, esal))
            {
              println("Records Updated")
            }
         }
         else if(mode==4)
          {
            println("Enter Employee ID = ")
            var eId=Console.readInt()
            if(employeeObj.deleteEmployeeData(eId))
            {
              println("Records Deleted")
            }
          }
         else
         {
          println("Invalid Choice")
         }
         
         log.log(Level.FINE, "Finer logged with mode of operation = "+mode);
       }
       catch
       {
         case e =>
           { 
             log.log(Level.SEVERE, "Exception occured ", e)     
           }
      } 
   }
}