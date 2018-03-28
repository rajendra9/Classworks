package htc_seed4_Scala
import java.util.Scanner
 

object ptmtch {
  
  def main(args: Array[String]){
    val sc=new Scanner(System.in)
    println("Enter an integar value within 0 to 12: \n")
    var x = sc.nextInt();
    println(matching(x))
    
  }
  
  def matching(A:Int):Any = A match{
    case 0=>"This belongs to set 0\n"
    case 1=>"This belongs to set 1\n"
    case 2=>"This belongs to set 2\n"
    case 3=>"This belongs to set 3\n"  
    case 4=>"This belongs to set 4\n"  
    case 5=>"This belongs to set 5\n"  
    case 6=>"This belongs to set 6\n"
    case 7=>"This belongs to set 7\n"
    case 8=>"This belongs to set 8\n"
    case 9=>"This belongs to set 9\n"
    case 10=>"This belongs to set 10\n"
    case 11=>"This belongs to set 11\n"
    case 12=>"This belongs to set 12\n"
    case _=>"This does not belong to any set\n"   
  }
  
}