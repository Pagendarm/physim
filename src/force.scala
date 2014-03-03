// A data structure and
// series of methods for
// creating, modyfying and
// applying forces

package physics {
    
    import general.Vector3
    class Force (var value: Vector3, var duration : Int) { 
    // duration is in milliseconds and must be positive
        require (duration > 0, "Duration must initilized to > 0")

        // Modifier

        // Decreases the duration by one millisecond 
        def decrease : Unit = {
            duration -= 1
        }
    
        // Prints value and duration of force
        override def toString: String = "["+value+" | "+duration+"]"

    }

}
