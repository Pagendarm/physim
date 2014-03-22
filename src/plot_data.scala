// 
// Class that takes
// a series of lists and labels
// and produces a file
// which can be read back
// to recreate this data structure
// it can also be fed to
// grapher.Graph to produce
// HTML/CSS graphs
//

package grapher {

    // Constructor takes a 
    // name of the object/context being added
    // list of the labels for the data e.g.
    // 'Time' 'Distance(m)'
    class PlotData (val name : String, val labels : List[String]) {
        var points = List() : List[List[AnyVal]]

        // Setter Methods

        // Preconditions:
        // 1. the number of lists passed in must be
        // equal to the number of labels
        // 2. the length of each of those lists must be 
        // identical
        
        // if the preconditions are not-met
        // data is unchanged
        // if data is already set the vaule is old value
        // is replaced
        // @REFACTOR w/ option

        def set_data (in : List[List[AnyVal]]) : Unit = {
            if (in.length == labels.length) {
                var len = in.head.length
                if (in.forall(x => x.length == len)) points = in
            }
        }

        override def toString () : String = {
            var s = "% \nname: "  : String
            s = s + name + "\n"
            var count = 0 : Int
            for (name <- labels) {
                s = s + count + "\t" + name + "\n"
                count = count + 1
            }
            s = s + "% \n$ \n" 
            if (!points.isEmpty) {
                var i = 0
                for (i <- 0 to (points.head.length - 1)) {
                    for (l <- points) {
                        s = s + l(i) + "\t" 
                    }
                    s = s + "\n"
                }
            }
            s + "$ \n"
        }

    }
}
