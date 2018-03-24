import scala.util.control._

object string1 {
  
  def main(args: Array[String])
  {
    
    var senten:String="0";
    println("Enter a sentence:");
    senten=Console.readLine();
    var chresult=Array[Char]();
    chresult=senten.toCharArray();
    var lengt= senten.length();
    var cond=0;
    var count:Int=0;
    
    if(lengt<10)
    {
      println("the sentence should have minimum of 10 characters");
    }
    else
    {
      println( "String Length is : " + lengt );
      var (i:Int,j:Int)=Pair(0,0);
      for(i<-0 to chresult.length-1)
      {
        if(chresult(i)==' ')
        {
          count=count+1;    
        } 
      }
      println("count"+count)
      if(count<1)
      {
         println("The sentence should have atleast one space");
      }
      else
      {
        for(i<-0 to chresult.length-2)
        {
          if(chresult(i)=='.')
          {
            cond=1;
            
          }
          else
          {
            cond=0;
          }
        }
          if(cond==0)
          {
            for(i<-0 to chresult.length-1)
            {
              if(chresult(0)==' ' && chresult(chresult.length-1)==' ')
              {
                println("The sentence should not start or end with space...")
              }
              else
              {
                if(chresult(chresult.length-1)=='.')
                {
                  println("the sentence is correct....");
                  Breaks;
                }
                else
                {
                   println("the sentence should end with a dot....");
                   Breaks;
                }
               
              }
             
            }
          }
          else
          {
            println("there should not be any dot in between...");
          }
              
      }
     
    }
  }
}