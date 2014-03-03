//
// Thing class to
// describe the items and
// objects in the simulation
//

package physics {

    import general._
    case class Thing(   mass : Double, 
                        position : Vector3 ,
                        velocity : Vector3 ,
                        forces : Array[Force]) {


    }

}
