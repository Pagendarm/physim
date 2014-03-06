// 
// Class that takes
// a series of lists and labels
// and produces a printout 
// or file that can be read 
// and transformed into a graph
//

package grapher {
    class Writer (val labels : List[String]) {
        var data = List() : List[List[AnyVal]]

        // Setter Methods
        def set_data (d : List[List[AnyVal]]) : Unit = {
            data = d
        }

        override def toString () : String = {
            var s = "% \n" : String
            var count = 0 : Int
            for (name <- labels) {
                s = s + count + "\t" + name + "\n"
                count = count + 1
            }
            s = s + "% \n$ \n" 
            if (!data.isEmpty) {
                var i = 0
                for (i <- 0 to (data.head.length - 1)) {
                    for (l <- data) {
                        s = s + l(i) + "\t" 
                    }
                    s = s + "\n"
                }
            }
            s + "$ \n"
        }

    }
}
