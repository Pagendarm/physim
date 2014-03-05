// Defines the enviroment class 
// (and its subclass(es) for running the
// simulation

package physics {
    import general._
    import general.Vector3
    class Enviroment (
        protected val time_limit : Long // how long simulation runs
        protected val delta : Long ) // the resolution of data storage 
        {
        protected var time = 0 : Long // current time of simulation

        // Note that persistent forces still are effected by duration
        // but are general to all things in the enviroment
        protected var persistent_forces = new Array(0) : Array[Force]

        import scala.collection.immutable.Hashmap
        protected var things = new Hashmap () : Hashmap[String,Record]

        def add_thing (thing: Tuple2[Thing,Array[Force]] ): Unit = 
        thing._1 match {
            case Thing (k,m,i_p,i_v) => things = things + (thing._1.key, 
                        new Record (m,thing._2,
                        new Array(Tuple3(0,i_p,i_v)))
                        )
        }

        def add_force (force: Force)

        // Computes the change in velocity (acceleration)
        def deltaV (f : Vector3, m: Double) : Vector3 = (f * (1.0 / m))

        // Computes the net force by summing over an array of forces
        def netF (  total: Vector3, 
                    forces : Array[Force]): Vector3 = 
            forces match {
            case head::tail =>  if (head.duration < time) {
                                    netF (total + head.value, tail) }
                                else netF (total, tail)
            case Nil        => total
        }

        // Simulate
 
        // Inner class to record each thing's changes
        protected class Record ( val mass : Double, 
                        var forces : Array[Force] , 
                        var log : Array[(Long, Vector3, Vector3)] ) {

            def update (): Unit = {
            // Either we need to fully update the record
            // with the new values
                if ( time % delta == 0 ) {
                    log =   (   time, 
                            log(0)._2 + log(0)._3 , 
                            log(0)._3 * delta_v( 
                                    netF( 
                                    netF ( Vector3(0,0,0), forces), 
                                    persistent_forces ) ) 
                            ) +: log 
                
               }
            // Or we need to just read from the 'top' and update it
            // in place
                else {
                    log (0) = (   time, 
                            log(0)._2 + log[0]._3 , 
                            log(0)._3 * delta_v( 
                                    netF( 
                                    netF ( Vector3(0,0,0), forces), 
                                    persistent_forces ) ) 
                            )
                }
            }
        }
    }
}
