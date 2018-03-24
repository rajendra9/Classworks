/*Program to provide definition for Email Validation*/

class EmailValidation
{
  def emailValidation(email:String):Boolean={
    
    var len=email.length()
    var emailArr=new Array[Char](len)
    var count:Int=0
    
    //Converting the email id into character array.
    emailArr=email.toCharArray()
    
    //Check whether Email-ID starts with a '.' or a '@'
    if(emailArr(0)!='.' && emailArr(0)!='@')
    {
      //Check whether the Email-ID ends with an 'm' or 'n'
      if(emailArr(len-1)=='m' || emailArr(len-1)=='n')
      {
        //Check if it's length is minimum 10 and maximum 30  
        if(len>=10&&len<=30)
        {
          for(i <- 0 to len-1)
          {
            if(emailArr(i)=='@')
            {
              count=count+1
            }
          }
          //Check whether Email-ID has only one @
          if(count==1)
          {
            var dot=emailArr.indexOf('.')
            var at=emailArr.indexOf('@')
            var under=emailArr.indexOf('_')
            
            if(emailArr.contains('.'))
            {
              //Check if '.' comes after '@' but not immediately
              if(dot>=at+2)
              {
                if(emailArr.contains('_'))
                {
                  //Check if '_' comes before '@'
                  if(under<at){
                    return true
                  }
                  else{
                    return false
                  }
                }
                return true
              }
            }
        }
      }
    }
  }
 return false
}
}

object EmailValidation {
  def main(args:Array[String]){
    
    //Initializing object of EmailValidation class
    val obj=new EmailValidation()
    
    //Caliing emailValidation method
    println(obj.emailValidation("srinath_@gmail.com"))
  }
}