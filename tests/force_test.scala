// Basic test file
// for Force and
// Thing classes
// also tests convert

import physics._
import general._
import general.Constants._
import general.Convert._

object ForceTest {
    def main(args: Array[String]) {

        val gravity = Force (Vector3(0,0,9.8),1)
        println (gravity)

        val columb = Force (Vector3(convert(1,KILO),0,0),
                                time_convert(1, BASE))
        println (columb)

        val ball = Thing ("Ball",10,new Vector3(ZERO_V),Vector3(0,0,0))

        println (ball)
        
        // Checking negative duration requirement
        //val error = new Force (Vector3(0,0,0), -1)

    }
}
