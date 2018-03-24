package com.htc

class EmployeeEntity 
{
     //Data Members
     var _empId=0
     var _empName=""
     var _salary=0.0
    
     //Defining Getters
    def empId=_empId
    def empName=_empName
    def salary=_salary
  
    //Defining Setters
    def empId_=(value:Int)=_empId=value
    def empName_=(value:String)=_empName=value
    def salary_=(value:Double)=_salary=value
  
    //constructor
    def this(eNo:Int,eName:String,eSal:Double){
      this()
      this._empId=eNo
      this._empName=eName
      this._salary=eSal
    }
   
   //Overriding ToString()
   override def toString():String=
   {
     return this._empId+"\n"+this._empName+"\n"+this._salary
   }
   
   //Overriding HashCode()
   override def hashCode:Int=
   {
     return _empName.hashCode()
   }
   
   //Overriding Equals()
   override def equals(EmployeeEntity:Any):Boolean=
   {
     EmployeeEntity match
     {
       case e : EmployeeEntity => _empId.equals(e._empId)
       case _ => false
     }
     
   }
}