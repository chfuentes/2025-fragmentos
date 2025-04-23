package cl.cfuentes.cargafragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cl.cfuentes.cargafragmentos.databinding.FragmentFrag1Binding
import com.google.android.material.snackbar.Snackbar

class Frag1Fragment : Fragment() {
    private var _binding : FragmentFrag1Binding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFrag1Binding.inflate(inflater,container,false)
        binding.btnFrag1Saludo.setOnClickListener {
            saludame()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saludame(){
        //Toast.makeText(context,"Click en Fragmento",Toast.LENGTH_LONG).show()
        Snackbar.make(binding.btnFrag1Saludo,"Click en Fragmento",Snackbar.LENGTH_SHORT).show()
    }


}