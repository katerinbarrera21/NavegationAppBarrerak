package DataSqlLite

class Registros {
    abstract  class Resultados{
        companion object{
            val _ID ="_id"
            val _RESULTADO = "resultado"
            val _TABLE_NAME = "historialCalculadora"
            var historial: MutableList<Double> = ArrayList()
        }
    }


}