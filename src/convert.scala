//
// Convert functions do the following:
// 1. (convert and mass convert)
// double SI values to the 'base' unit (gram, seconds, meters)
// e.g. 10, kilo == 10,000
// 
// 2. time_convert converts time to milliseconds as Int
//
// 3. distance_convert converts distances to millimeters as Float


package general {
    object Convert extends Enumeration {
        type Prefix = Value
        val PICO = Value("Pico")
        val NANO = Value("Nano")
        val MICRO = Value("Micro")
        val MILLI = Value("Milli")
        val CENTI = Value("Centi")
        val DECI = Value("Deci")
        val BASE = Value("Base")
        val DECA = Value("Deca")
        val HECTO = Value("Hecto")
        val KILO = Value("Kilo")
        val MEGA = Value("Mega")
        val GIGA = Value("Giga")
        val TERA = Value("Tera")


        import math.pow

        
        def convert (value : Double , prefix : Prefix): Double = 
        prefix match {
            case PICO   => (value * pow(10.0, -12)) 
            case NANO   => (value * pow(10.0, -9))
            case MICRO   => (value * pow(10.0, -6))
            case MILLI   => (value * pow(10.0, -3))
            case CENTI   => (value * pow(10.0, -2))
            case DECI   => (value * pow(10.0, -1))
            case DECA   => (value * pow(10.0, 1))
            case HECTO   => (value * pow(10.0, 2))
            case KILO   => (value * pow(10.0, 3))
            case MEGA   => (value * pow(10.0, 6))
            case GIGA   => (value * pow(10.0, 9))
            case TERA   => (value * pow(10.0, 12))
            case _      => throw new IllegalArgumentException (
                            "Requires a valid Prefix enum")
        }

        def mass_convert (value : Double , prefix : Prefix): Double = 
        prefix match {
            case PICO   => (value * pow(10.0, -12)) 
            case NANO   => (value * pow(10.0, -9))
            case MICRO   => (value * pow(10.0, -6))
            case MILLI   => (value * pow(10.0, -3))
            case CENTI   => (value * pow(10.0, -2))
            case DECI   => (value * pow(10.0, -1))
            case DECA   => (value * pow(10.0, 1))
            case HECTO   => (value * pow(10.0, 2))
            case KILO   => (value * pow(10.0, 3))
            case MEGA   => (value * pow(10.0, 6))
            case GIGA   => (value * pow(10.0, 9))
            case TERA   => (value * pow(10.0, 12))
            case _      => throw new IllegalArgumentException (
                            "Requires a valid Prefix enum")
        }

        def distance_convert (value: Double, prefix : Prefix): Double =
        prefix match {
            case PICO   => (value * pow(10.0, -9)) 
            case NANO   => (value * pow(10.0, -6))
            case MICRO   => (value * pow(10.0, -3))
            case MILLI   => (value)
            case CENTI   => (value * pow(10.0, 1))
            case DECI   => (value * pow(10.0, 2))
            case BASE   => (value * pow(10, 3))
            case DECA   => (value * pow(10.0, 4))
            case HECTO   => (value * pow(10.0, 5))
            case KILO   => (value * pow(10.0, 6))
            case MEGA   => (value * pow(10.0, 9))
            case GIGA   => (value * pow(10.0, 12))
            case TERA   => (value * pow(10.0, 15))
            case _      => throw new IllegalArgumentException (
            "Requires a valid Prefix enum, with magnitude larger then milli"
            )
        }
        // convert value to mili seconds,
        //only works on prefixes greater then
        // milli
        def time_convert (value: Int, prefix : Prefix): Int =
        prefix match {
            case MILLI   => (value)
            case CENTI   => (value * pow(10.0, 1).toInt)
            case DECI   => (value * pow(10.0, 2).toInt)
            case BASE   => (value * pow(10, 3).toInt)
            case DECA   => (value * pow(10.0, 4).toInt)
            case HECTO   => (value * pow(10.0, 5).toInt)
            case KILO   => (value * pow(10.0, 6).toInt)
            case MEGA   => (value * pow(10.0, 9).toInt)
            case GIGA   => (value * pow(10.0, 12).toInt)
            case TERA   => (value * pow(10.0, 15).toInt)
            case _      => throw new IllegalArgumentException (
            "Requires a valid Prefix enum, with magnitude larger then milli"
            )
        }

   } 
}
