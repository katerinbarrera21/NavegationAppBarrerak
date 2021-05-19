package ec.edu.ups.navegacionapp

import DataSqlLite.DataSQL
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ec.edu.ups.navegacionapp.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var num1: Double = 0.0
    private var num2: Double = 0.0
    private var operacion: Int = 0
    private lateinit var binding: FragmentHomeBinding
    var db: DataSQL? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        //Logica para obtener resultado
        binding.buttonResultado.setOnClickListener{
            var resultado = when(operacion){
                suma -> num1 + num2
                resta -> num1 - num2
                multiplicacion -> num1 * num2
                division -> num1 / num2
                else -> 0
            }

            guardarHistorial(resultado as Double)
            var valores = recupearHistorial()

            //binding.textResultado.text = valores.toString()
            num1 = resultado as Double
            num2 = 0.0
            binding.textResultado.text = resultado.toString()
        }

        db = DataSQL(this)

        binding.buttonContinuar.setOnClickListener { view: View ->
            //view.findNavController().navigate(R.id.action_homeFragment_to_datosPersonalesFragment)
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDatosPersonalesFragment())

        }

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }


    //Funcion para guardar el resultado con clave-valor
    private fun guardarHistorial(resultado: Double){
        db!!.insert(resultado)
    }

    //Funcion para guardar el resultado con clave-valor
    private fun recupearHistorial(): MutableList<Double>{
        return db!!.getData()
    }

    //Funcion para eliminar todo el historial
    private fun eliminarHistorial(){

    }

    private fun numeroPresionado(digito: String){

        binding.textResultado.text = "${binding.textResultado.text}$digito"
        if (operacion == no_operacion){
            num1 = binding.textResultado.text.toString().toDouble()
        }else{
            num2 = binding.textResultado.text.toString().toDouble()
        }

    }

    private fun operacionPresionado(operacion: Int){
        this.operacion = operacion
        //num1 = textResultado.text.toString().toDouble();
        binding.textResultado.text = "";
    }

    companion object{
        const val suma = 1;
        const val resta = 2;
        const val multiplicacion = 3;
        const val division = 4;
        const val no_operacion = 5;

    }

}