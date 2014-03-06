// Primary test file for
// all classes, particullarly
// Enviroment and the grapher package

import physics._
import general._
import general.Constants._
import general.Convert._

object PrimaryTest {
    def main (args: Array[String]) {
        val push = Force (Vector3 (10,0,0),time_convert(3,BASE))
        val zero = Vector3(0,0,0)
        val box = Thing ("Box",mass_convert(2,KILO),zero,zero)

        val world = new Enviroment (time_convert(4,BASE), 
                                    time_convert(1,BASE))

        world add_thing (box, List(push))
        
        println ("Before running sim:")
        println (world)

        world simulate

        println ("After:")
        println (world)


    }
}
