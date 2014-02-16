//
// Convert does the following:
// 1. converts double SI values to the 'base' unit
// e.g. 10, kilo == 10,000
//

package general {
    object Convert {
        object Prefix extends Enumeration {
        type Prefix = Value
        val pico = Value("Pico")
        val nano = Value("Nano")
        val micro = Value("Micro")
        val milli = Value("Milli")
        val centi = Value("Centi")
        val deci = Value("Deci")
        val deca = Value("Deca")
        val hecto = Value("Hecto")
        val kilo = Value("Kilo")
        val mega = Value("Mega")
        val giga = Value("Giga")
        val tera = Value("Tera")
        }


    }
}
