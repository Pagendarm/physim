// Defines the enviroment class 
// (and its subclass(es) for running the
// simulation

package physics {
    import general._
    import general.Vector3
    import scala.collection.immutable._
    class Environment (
        protected val time_limit : Long , // how long simulation runs
        protected val delta : Long )    // the resolution of data storage 
        {
        protected var time = 1 : Long // current time of simulation

        // Note that persistent forces still are effected by duration
        // but are general to all things in the enviroment
        protected var persistent_forces = List(): List[Force]

        protected var things = ListMap(): ListMap[String,Record]
        
        // Setter methods

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
        
        // Helper Methods for Physics Calculations

        // Computes the change in velocity (acceleration)
        def delta_v (f : Vector3, m: Double) : Vector3 = (f * (1.0 / m))

        // Computes the net force by summing over an list of forces
        def net_f (  total: Vector3, 
                    forces : List[Force]): Vector3 = 
            forces match {
            case head :: tail =>  if (time < head.duration) {
                                    net_f (total + head.value, tail) }
                                else net_f (total, tail)
            case Nil        => total
        }

        // Simulate
        def simulate (): Unit = {
            val sim = (e : Tuple2[String,Record]) => e._2.update
            while (time <= time_limit) {
               things foreach sim
               time=time+1
            }
        }

        // PlotData type from the log
        // of an item
        // select: should be 
        // 0 for Time and Velocity points
        // 1 for Time and Position points
        // 2 for all 7 lists, Time, Velocity, Postion

        // All long values are converted to double
        // so that printout is more readable
        // this results in loss or precision
        import grapher.PlotData
        def create_plot_data (name : String, select: Int) : PlotData = { 
            type LogT = Tuple3[Long,Vector3,Vector3]
            type ListF = List[Float]
            val log = things.get(name).get.log.tail : List[LogT]
            var labels = List() : List[String]
            var data = List() : List[List[AnyVal]]
            if (select == 2 || select == 0) {

            val vx= log reverseMap ((e : LogT) => e._3.x.toFloat) : ListF
            val vy= log reverseMap ((e : LogT) => e._3.y.toFloat) : ListF
            val vz= log reverseMap ((e : LogT) => e._3.z.toFloat) : ListF

                labels = "xVelocity" :: "yVelocity" :: "zVelocity" :: labels
                data   =  vx :: vy  :: vz :: data
            }
            if (select == 2 || select  == 1) {

            val x= log reverseMap ((e : LogT) => e._2.x.toFloat) : ListF
            val y= log reverseMap ((e : LogT) => e._2.y.toFloat) : ListF
            val z= log reverseMap ((e : LogT) => e._2.z.toFloat) : ListF

                labels = "xPosition" :: "yPosition" :: "zPosition" :: labels
                data   =  x :: y  :: z :: data
            }
            // Time
            val t = log reverseMap ((e : LogT) => e._1) : List[Long]
            labels = "Time" :: labels
            data = t :: data

            // Create PlotData
            val pd = new PlotData (name,labels)
            pd.set_data(data)
            pd
        }
 
        override def toString: String = things toString

        // Inner class to record each thing's changes
        protected class Record ( val mass : Double, 
                        var forces : List[Force] , 
                        var log : List[(Long, Vector3, Vector3)] ) {

            override def toString: String = log toString
            
            // Post-condition: update() ends with log.head
            // containing the most recent value
            // and log.tail contains the log values
            // based on delta
            def update (): Unit = {
            // Always update head of the list
                log = (   time, 
                            log.head._2 + log.head._3 , 
                            log.head._3 + delta_v( 
                                    net_f ( 
                                    net_f ( Vector3(0,0,0), forces), 
                                    persistent_forces) , mass) 
                            ) :: (log.tail)
            // But if its time to keep the value
            // then we prepend a copy
                if ( time % delta == 0 ) {
                    log = log.head :: log 
                }
            }
        }
        
    }
}
