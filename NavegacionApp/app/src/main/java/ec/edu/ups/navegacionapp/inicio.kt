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
 * Use the [inicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class inicio : Fragment() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}