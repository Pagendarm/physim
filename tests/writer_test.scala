//
// Test file for writer
//

import grapher._

object WriterTest {
    def main (args: Array[String]) {
    val l1 = List ("x", "y","z")

    val w = new Writer (l1)
    val xs = List (1,2,3,4)
    val ys = List (0,1,0,1)
    val zs = List (0,0,0,0)
    w.set_data (List(xs,ys,zs))
    println(w)
    }
}
