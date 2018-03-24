

package com.htc

class InvalidSalaryException(s:String) extends Exception{
  override def getMessage():String={
    return s
  }
}