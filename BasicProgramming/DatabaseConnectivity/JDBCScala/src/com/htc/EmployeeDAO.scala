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
   def insert(empObj:EmployeeEntity):Boolean={
     
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
   def findEmployee(eId:Int):String={
     var empObj=new EmployeeEntity()
     var sb=new StringBuilder()
     var output:String=""
     //Calling the Stored Procedure
     var cs:CallableStatement=connectionObj.prepareCall("{call UspFindEmployee(?)}")
     
     
       cs.setInt(1, eId)
       var resultSet:ResultSet=cs.executeQuery()
      
       while(resultSet.next()){
         output=resultSet.getString("empName")+resultSet.getDouble("salary")
       }
        if(output==""){
    
         throw new EmployeeNotFound("Employee Not Found")     
       }
      return output
       /*println(resultSet.getString(2)+resultSet.getDouble(3))
       if(cs.execute()==false){
         println(cs.getString(2)+"\n"+cs.getDouble(3))
          return cs.getString(2)+"\n"+cs.getDouble(3)
       }
       else
       throw new EmployeeNotFound("Employee Not Found")*/
     
   }
   
   //Updating Employee Details
   @throws(classOf[EmployeeNotFound])
   @throws(classOf[InvalidSalaryException])
   def updateEmployee(eId:Int,newSalary:Double):Boolean={
     
     //Calling the Stored Procedure
     var cs:CallableStatement=connectionObj.prepareCall("{call UspUpdateEmployeeSalary(?,?)}")
     
    
       cs.setInt(1, eId)
       cs.setDouble(2,newSalary)
       if(newSalary<0){
         throw new InvalidSalaryException("The Salary you entered is Invalid")
       }
       var count:Int=cs.executeUpdate()
       if(count<=0){
         throw new EmployeeNotFound("Employee Not Found")
       }
       println(count)
       return true
    
     
   }
   
   //Deleting Employee Details
   @throws(classOf[EmployeeNotFound])
   def deleteEmployee(eId:Int):Boolean={
     
     //Calling the Stored Procedure
     var cs:CallableStatement=connectionObj.prepareCall("{call UspDeleteEmployee(?)}")
     
       cs.setInt(1,eId)
       var count:Int=cs.executeUpdate()
       if(count<=0){
         throw new EmployeeNotFound("Employee Not Found")
       }
       return true
   }
   
}