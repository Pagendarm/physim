package physics { 
    import general.Vector3

    // Force class to
    // describe the forces acting
    // on items
    
    case class Force (value: Vector3, duration : Int) { 
    // duration is in milliseconds and must be positive
        require (duration > 0, "Duration must initilized to > 0")
    
        // Prints value and duration of force
        override def toString: String = "["+value+" | "+duration+"]"

    }

    // Thing class to
    // describe the items and
    // objects in the simulation
    // when they are created

    case class Thing(   key : String,
                        mass : Double, 
                        initial_position : Vector3 ,
                        initial_velocity : Vector3 ) {

        // Prints key, mass
        override def toString: String = "{"+key+" | "+mass+"}"

    }

}
