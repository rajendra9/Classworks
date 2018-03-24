
import java.sql.{Connection,DriverManager,ResultSet}
object JDBCSelectExample 
{
  def main(args:Array[String])
  {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    
    val conn=DriverManager.getConnection("jdbc:sqlserver://IMPC2364;databaseName=Northwind;user=sa;password=123Welcome;useUnicode=true;characterEncoding=UTF-8")
    try
    {
      val statement=conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY)
      val rs=statement.executeQuery("Select * from Categories")
      while(rs.next())
      {
        println(rs.getString("CategoryID")+" "+rs.getString("CategoryName"))
      }
    }
    catch
    {
        case e=>e.printStackTrace()
    }
    finally
    {
        conn.close()
    }
    }
}
