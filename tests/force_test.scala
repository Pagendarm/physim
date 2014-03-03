// Basic test file
// for Force class
//

import physics._
import general._
import general.Convert._

object ForceTest {
    def main(args: Array[String]) {

        val gravity = new Force (Vector3(0,0,9.8),1)
        println (gravity)
        gravity.decrease
        println (gravity)

        val c = new Force (Vector3(convert(1,KILO),0,0),
                                time_convert(1, BASE))
        println (c)
        
        // Checking negative duration requirement
        //val error = new Force (Vector3(0,0,0), -1)

    }
}
