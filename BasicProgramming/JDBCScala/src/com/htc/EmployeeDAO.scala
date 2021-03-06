package com.htc

import java.sql.CallableStatement
import java.sql.Statement
import java.sql.ResultSet
import javax.lang.model.util.Types

//Class for implementing Trait
class EmployeeDAO extends IEmployeeServiceProvider {
   var obj = new DBConnector()
   val  connectionObj = obj.getConnection()
   var statement = connectionObj.createStatement()
   
   //Inserting Employee Details
   
   @throws(classOf[InvalidSalaryException])
   def insertEmployeeData(empObj:EmployeeEntity):Boolean={
     
     //Calling the Stored Procedure
     var cs:CallableStatement=connectionObj.prepareCall("{call UspInsertEmployee(?,?)}")
     
       cs.setString(1, empObj._empName)
       cs.setDouble(2, empObj._salary)
       if(empObj._salary<0){
         throw new InvalidSalaryException("The Salary you entered is Invalid")
       }
       var count:Int=cs.executeUpdate()
       if(count>0)
       {
         return true  
       }
       return false
     
   }
   
   //Read Employee Details
   @throws(classOf[EmployeeNotFound])
   def findEmployeeData(eId:Int):String=
   {
       var empObj=new EmployeeEntity()
       var sb=new StringBuilder()
       var output:String=""
     
       //Calling the Stored Procedure
       var cs:CallableStatement=connectionObj.prepareCall("{call UspFindEmployee(?)}")
       cs.setInt(1, eId)
       var resultSet:ResultSet=cs.executeQuery()
       while(resultSet.next())
       {
         output=resultSet.getString("empName")+resultSet.getDouble("salary")
       }
       if(output=="")
       {
         throw new EmployeeNotFound("You have entered a wrong Employee ID")     
       }
       
      return output
   }
   
   //Updating Employee Details
   @throws(classOf[EmployeeNotFound])
   @throws(classOf[InvalidSalaryException])
   def updateEmployeeData(eId:Int,newSalary:Double):Boolean={
     
     //Calling the Stored Procedure
     var cs:CallableStatement=connectionObj.prepareCall("{call UspUpdateEmployeeSalary(?,?)}")
     
    
       cs.setInt(1, eId)
       cs.setDouble(2,newSalary)
       if(newSalary<0){
         throw new InvalidSalaryException("The Salary you entered is Invalid")
       }
       var count:Int=cs.executeUpdate()
       if(count<=0){
         throw new EmployeeNotFound("You have entered a wrong Employee ID")
       }
       return true
    
     
   }
   
   //Deleting Employee Details
   @throws(classOf[EmployeeNotFound])
   def deleteEmployeeData(eId:Int):Boolean={
     
     //Calling the Stored Procedure
     var cs:CallableStatement=connectionObj.prepareCall("{call UspDeleteEmployee(?)}")
     
       cs.setInt(1,eId)
       var count:Int=cs.executeUpdate()
       if(count<=0){
         throw new EmployeeNotFound("You have entered a wrong Employee ID")
       }
       return true
   }
   
}