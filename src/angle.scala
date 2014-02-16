//
// Object for Angles
// and converting
// from DEG to RAD
//

package general {
    // Enumeration for Angle
    import math._
    object Angle extends Enumeration {
        type Angle = Value
        val RAD = Value("Radians")
        val DEG = Value("Degrees")

        // converts angles to radians
        // and visa versa : uses math.toDegrees
        // and math.toRadians
        // e.g. 180, DEG  == PI
        def convert (value: Double, from: Angle): Double = from match {
            case RAD => toDegrees(value)
            case DEG => toRadians(value)
            case _ => throw new IllegalArgumentException (
                        "Requires an valid Angle enum")
        }

    }
}
