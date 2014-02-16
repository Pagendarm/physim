// Test file for
// Vector3

import general._
import general.Angle._
import general.Rotate_Axis._

object Vector3Test {

    def main (args : Array[String]) {
        val v = Vector3(5,10,20)
        val v2 = new Vector3(v)
        val v3 = (v + v2)
        val v4 = (v * 2)
        val t = v.getTuple
        val vt = new Vector3(t)
        val ux = new Vector3(1,0,0)
        val rotX = ux.rotate(convert(90,DEG),X)
        val rotY = ux.rotate(convert(90,DEG),Y)
        val rotZ = ux.rotate(convert(90,DEG),Z)
        println("Original: "+v)
        println("Tuple: "+t)
        println("Tuple Constructed: "+vt)
        println("Should be doubled(add): " +v3)
        println("Should be doubled(mult): "+v4)
        println ("Doubled version equal: "+ (v3==v4))
        println ("Test with null: "+ v3.equals(null))
        println ("Unit Vector x: " +ux)
        println ("Rotated 90 degrees on X: " +rotX)
        println ("Rotated 90 degrees on Y: " +rotY)
        println ("Rotated 90 degrees on Z: " +rotZ)
    }


}
