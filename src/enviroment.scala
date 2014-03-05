// Defines the enviroment class 
// (and its subclass(es) for running the
// simulation

package physics {
    import general._
    import general.Vector3
    import scala.collection.immutable._
    class Enviroment (
        protected val time_limit : Long , // how long simulation runs
        protected val delta : Long )    // the resolution of data storage 
        {
        protected var time = 0 : Long // current time of simulation

        // Note that persistent forces still are effected by duration
        // but are general to all things in the enviroment
        protected var persistent_forces = List(): List[Force]

        protected var things = ListMap(): ListMap[String,Record]

        def add_thing (thing: Tuple2[Thing,List[Force]] ): Unit = 
        thing._1 match {
            case Thing (k,m,i_p,i_v) => {
                var arr = List( Tuple3(0 : Long, i_p, i_v) )
                val rec = new Record (m , thing._2, arr)
                things = things + Tuple2(thing._1.key, rec)
                                        }
        }

        def add_force (force: Force) : Unit = {
            persistent_forces = force :: persistent_forces
        }

        // Computes the change in velocity (acceleration)
        def delta_v (f : Vector3, m: Double) : Vector3 = (f * (1.0 / m))

        // Computes the net force by summing over an list of forces
        def net_f (  total: Vector3, 
                    forces : List[Force]): Vector3 = 
            forces match {
            case head :: tail =>  if (head.duration < time) {
                                    net_f (total + head.value, tail) }
                                else net_f (total, tail)
            case Nil        => total
        }

        // Simulate
 
        // Inner class to record each thing's changes
        protected class Record ( val mass : Double, 
                        var forces : List[Force] , 
                        var log : List[(Long, Vector3, Vector3)] ) {

            def update (): Unit = {
            // Either we need to fully update the record
            // with the new values
                if ( time % delta == 0 ) {
                    log = (   time, 
                            log.head._2 + log.head._3 , 
                            log.head._3 + delta_v( 
                                    net_f ( 
                                    net_f ( Vector3(0,0,0), forces), 
                                    persistent_forces) , mass) 
                            ) :: log 
                
               }
            // Or we need to just read from the 'top' and update it
            // in place
                else {
                    log = (   time, 
                            log.head._2 + log.head._3 , 
                            log.head._3 + delta_v( 
                                    net_f ( 
                                    net_f ( Vector3(0,0,0), forces), 
                                    persistent_forces) , mass) 
                            ) :: (log.tail)
                }
            }
        }
    }
}
