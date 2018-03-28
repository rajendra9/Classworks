

package com.htc

import java.sql.ResultSet

//Declaring Trait for implementing CRUD operations
trait IEmployeeServiceProvider {
  def insertEmployeeData(empObj:EmployeeEntity):Boolean @throws(classOf[InvalidSalaryException])
  def updateEmployeeData(eId:Int,newSalary:Double):Boolean @throws(classOf[EmployeeNotFound])
  def findEmployeeData(eId:Int):String @throws(classOf[EmployeeNotFound]) @throws(classOf[InvalidSalaryException])
  def deleteEmployeeData(eId:Int):Boolean @throws(classOf[EmployeeNotFound])
}