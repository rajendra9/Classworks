
//Program to check the Employee eligibility for on-site projects

object EmployeeEligibility {
  def main(args:Array[String]){
    println("Enter the Employee Evaluation Report :- ")
  print("Enter y if Passport available else Enter n")
  var passport=Console.readLine()
  if(passport=="y"){
    println("Enter age of Employee = ")
    var age:Int=Console.readInt()
    if(age<23){
      println("The employee is not eligible to go on-site")
    }
    else{
      println("Enter Experience = ")
      var experience:Int=Console.readInt()
      if(experience<2){
        println("The employee is not eligible to go on-site")
      }
      else{
        println("Enter Training Feedback = ")
        var trainingFeedback=Console.readLine()
        if(trainingFeedback=="fail"){
          println("The employee is not eligible to go on-site")
        }
        if(trainingFeedback=="pass"){
          println("Enter Communication Skills Status = ")
          var communication=Console.readLine()
          if(communication=="good"){
            println("The Employee is eligible for an on-site project")
          }
          else{
            println("The employee is not eligible to go on-site")
          }
        }
      }
    }
  }
  else{
    println("The employee is not eligible to go on-site")
  }
  }
  
}