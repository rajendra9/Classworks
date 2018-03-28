import scala.collection.mutable.ListBuffer
class University1(var deptList:ListBuffer[Departmentt]){
  var get_deptList:ListBuffer[Departmentt]=deptList
 override def toString="DeptList = "+get_deptList
  
  
  
}
class Departmentt(var deptNo:String,var departmentName:String,val professorList:List[Professors]){
  var _deptId:String=deptNo
  var _deptName:String=departmentName
  val _profList:List[Professors]=professorList
  def deptId=_deptId
  def deptName=_deptName
  def profList=_profList 
    override def toString="DeptNumber = "+this.deptId+"\n"+
  "DeptName = "+this.deptName+"\n"+
  "ProfList = "+this.profList
  
}
class Professors(var professorId:String,var professorName:String){
  var _profId:String=professorId
  var _profName:String=professorName
  def profId=_profId
  def profName=_profName
    override def toString="profId= "+this.profId+"\n"+
  "Prof Name = "+this.profName+"\n"

}

object Aggregation1{
  def main(args:Array[String]){
 

      var prof1=new Professors("P01","suji")
      var prof2=new Professors("P02","shalini")
      var prof3=new Professors("P03","silvia")
      var prof4=new Professors("P04","sreebha")
      var prof5=new Professors("P05","bindhu")
      var prof6=new Professors("P06","maha")
     
      var professorList1=List(prof1,prof2,prof3)
      var professorList2=List(prof4,prof5,prof6)
      var professorList3=List(prof2,prof3,prof4)
      var dept1=new Departmentt("D01","cse",professorList1)
      var dept2=new Departmentt("D02","ece",professorList2)
      
      var departList=ListBuffer(dept1,dept2)
      var univ=new University1(departList)
      var dept3= new Departmentt("D03","eee",professorList1)
    // println(departList)
      println("After removing")
      departList--= Seq(dept1)
      println(departList) 
      var resultantList=departList::(dept3::Nil)
      println("After adding dept3")
      println(resultantList)
    }
}