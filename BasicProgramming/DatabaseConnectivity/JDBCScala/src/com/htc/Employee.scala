package com.htc
import java.util
import java.util.Calendar
import java.util.Formatter.DateTime
import java.io.FileWriter


object Employee  
{
  def main(args: Array[String])
  {
    //initializing EmployeeEntity and EmployeeDAO objects
      var employeeObj = new EmployeeDAO()
      var empObj=new EmployeeEntity()
      var e1=new EmployeeEntity()
      var e2=new EmployeeEntity(12,"Anil Nair",13364.0)
      e1.empId_=(124)
      e1.empName_=("Anil Nair")
      e1.salary_=(13364.0)
      println("Implementing toString()")
      println(e1.toString())
      println("Implementing hashCode()")
      println(e1.hashCode())
      println("Implementing equals()")
      println(e1.equals(e2))
      println("1.Insert Employee Records")
      println("2.Get Employee Records")
      println("3.Update Employee Records")
      println("4.Delete Employee Records")
   
     println("\nEnter the mode of operation")
     var mode=Console.readInt()
   
     try
      {
       if(mode==1)
       {
         println("\nEnter Employee Name")
         var ename=Console.readLine()
         println("\nEnter Salary")
         var sal=Console.readDouble()
         empObj.empName_=(ename)
         empObj.salary_=(sal)
         if(employeeObj.insert(empObj))
         {
            println("Records Inserted")
         }
       }
       else if(mode==2)
       {
         println("Enter the employee ID to display details")
         var eId : Int = Console.readInt()
         println(employeeObj.findEmployee(eId))
       }
       else if(mode==3)
       {
         println("Enter Employee ID = ")
          var eId=Console.readInt()
          println()
          println("Enter New Salary value = ")
          var esal=Console.readDouble()
          if(employeeObj.updateEmployee(eId, esal))
          {
            println("Records Updated")
          }
          else
          {
            println("Updation Failed")
          }
       }
       else if(mode==4)
        {
          println("Enter Employee ID = ")
          var eId=Console.readInt()
          if(employeeObj.deleteEmployee(eId))
          {
            println("Records Deleted")
          }
        }
       else
       {
        println("Invalid Choice")
       }
   }
   catch
   {
     case e =>{ e.printStackTrace() 
     var logMessage="Date : "+Calendar.getInstance().getTime+", Exception Occured : "+e.getClass
     var start="--------------------------------------------------"
     var date="Date : "+Calendar.getInstance().getTime
     var exceptionType="Exception Occured : "+e.getClass
     var exceptionContent="Exception Description : "+e.getMessage
     val writer:FileWriter = new FileWriter(new java.io.File("D:/SrinathNair/Log.txt"),true)
     writer.append(start)
     writer.append(String.format("%n"))
     writer.append(date)
     writer.append(String.format("%n"))
     writer.append(exceptionType)
     writer.append(String.format("%n"))
     writer.append(exceptionContent)
     writer.append(String.format("%n%n"))
//     writer.append(logMessage)
//     writer.append(String.format("%n"))
     writer.close()
     println("Exceptions successfully logged into file.")
     }
  } 
}
}