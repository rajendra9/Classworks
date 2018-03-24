

package com.htc

import java.sql.ResultSet

//Declaring Trait for implementing CRUD operations
trait IEmployeeServiceProvider {
  def insert(empObj:EmployeeEntity):Boolean @throws(classOf[InvalidSalaryException])
  def updateEmployee(eId:Int,newSalary:Double):Boolean @throws(classOf[EmployeeNotFound])
  def findEmployee(eId:Int):String @throws(classOf[EmployeeNotFound]) @throws(classOf[InvalidSalaryException])
  def deleteEmployee(eId:Int):Boolean @throws(classOf[EmployeeNotFound])
}