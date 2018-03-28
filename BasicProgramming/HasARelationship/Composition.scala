
import scala.collection.mutable.ListBuffer


class University(var deptId:List[String],var deptName:List[String]){
 
  var deptlist=new ListBuffer[Department]() 
  var deptNo:List[String]=deptId
   var departName:List[String]=deptName
  for(index <- 0 to deptId.length-1){
     
      var dep=new Department(deptNo(index),departName(index))
      deptlist+=dep
     
  }
  //deptlist.toList
  override def toString="DeptList = "+this.deptlist
  
}


class Department(var deptNo:String,var departmentName:String){
  var _deptId:String=deptNo
  var _deptName:String=departmentName
    def deptId=_deptId
  def deptName=_deptName
  override def toString="DeptNumber = "+this.deptId+"\n"+
  "DeptName = "+this.deptName
}

object Compose{
  def main(args:Array[String]){
     var deptNo=List("D01","D02","D03")
     var deptName=List("CSE","ECE","EEE")
     var univ=new University(deptNo,deptName)
     println(univ.toString())
   
     
  }
}