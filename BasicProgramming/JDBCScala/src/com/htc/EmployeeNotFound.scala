

package com.htc

class EmployeeNotFound(s:String) extends Exception {
  override def getMessage():String={
    return s
  }
}