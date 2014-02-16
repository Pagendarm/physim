// A data structure and
// series of methods for
// manipulating 3-D
// vectors

package general {
    
    case class Vector3 (x: Double, y : Double, z : Double) {
        
        // Tuple3 Constructor
        def this (tuple : Tuple3[Double,Double,Double]) = {
            this (tuple._1,tuple._2,tuple._3)
        }

        // Copy Constructor
        def this (other : Vector3) = {
            this(other.x , other.y, other.z)
        }
        
        // Accesors
        def getTuple: (Double,Double,Double) = {
            (x, y, z)
        }

        // Immutable Operators

        // Vector Addition
        def + (other: Vector3): Vector3 = {
            Vector3 ( x + other.x , y + other.y, z + other.z)
        }

        // Vector (cross) Multiply
        def ** (other: Vector3): Double = {
         ((x*other.x) + (y*other.y) + (z*other.z))
        }

        // Scalar (dot) Multiply
        def * (c: Double): Vector3 = {
            Vector3 ( x*c , y*c , z*c )
        }

        import Rotate_Axis._
        import math._
        // Rotates a vector by an
        // angle around a given axis
        // if the axis of rotation is pointed
        // at the observer, 
        // poitive angles rotate counter clockwise
        // expects angle in radians

        // Thanks to: 
        // http://en.wikipedia.org/wiki/Rotation_matrix#Basic_rotations
        // http://inside.mines.edu/~gmurray/ArbitraryAxisRotation/

        def rotate (a: Double, axis: Rotate_Axis.Axis): Vector3 = {
        val transform : Tuple3[Vector3,Vector3,Vector3] = axis match {
            case X => ( Vector3(1,0,0),
                        Vector3(0, cos(a),-1 * sin(a)),
                        Vector3(0, sin(a), cos (a))
                        )
            case Y => ( Vector3(cos (a),0,sin(a)),
                        Vector3(0, 1, 0),
                        Vector3(-1 * sin(a), 0, cos (a))
                        )
            case Z => ( Vector3(cos (a),-1 * sin (a),0),
                        Vector3(sin (a), cos (a),0),
                        Vector3(0, 0, 1)
                        )
            case _ => throw new IllegalArgumentException (
                        "Requires an valid Axis enum")
        }
            Vector3 ( this ** transform._1 ,
                    this ** transform._2 ,
                    this ** transform._3)
        }

        // Other

        // toString overriden toString to eliminate Vector3 in default
        // toString, prints identically in tuple form
        override def toString: String = "("+x+","+y+","+z+")"

    }
}
