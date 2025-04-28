package cl.cfuentes.cargafragmentos

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import cl.cfuentes.cargafragmentos.databinding.FragmentFrag3Binding
import java.text.DecimalFormat

class Frag3Fragment : Fragment() {

    private var _binding : FragmentFrag3Binding?=null
    private val binding get() = _binding!!
    private var cantidad = 0
    private var valorparacalculo : Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFrag3Binding.inflate(inflater,container,false)
        //Inicializar Spinner con data
        inicializaSpinner()
        //Programar comportamiento boton agregar y quitar

        binding.btAgregar.setOnClickListener {
            cantidad++
            actualizarTextoCantidad()
            actualizarTotal()
        }

        binding.btQuitar.setOnClickListener {
            if(cantidad>0){
                cantidad--
            }
            actualizarTextoCantidad()
            actualizarTotal()
        }

        binding.btAgregar.setOnLongClickListener {
            cantidad += 100
            actualizarTextoCantidad()
            actualizarTotal()
            true // importante: indica que el evento fue consumido
        }

        binding.btQuitar.setOnLongClickListener {
            cantidad = maxOf(0, cantidad - 100)
            actualizarTextoCantidad()
            actualizarTotal()
            true
        }

        //Investigar Handler y Runnable para repetir el longclick o variar dependiendo de la duracion de la pulsacion

        return binding.root
    }

    private fun inicializaSpinner() {
        val unidades = listOf(
            "Metros",
            "Millas",       // ¡Este valor es el correcto para millas a kilómetros!
            "Pies",
            "Centímetros"
        )

        val equivalencias = listOf(
            1000.0,      // 1000 metros = 1 km
            1609.34,     // 1609.34 metros = 1 milla = 1 km
            3280.84,     // 3280.84 pies = 1 km
            100000.0     // 100000 cm = 1 km
        )

        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, unidades)
        /*val sizeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.unidades,
            android.R.layout.simple_spinner_item
        )*/
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spUnidadmedida.adapter = adapter

        //Ahora necesito guardar el valor que selecciono el usuario
        /*
        AdapterView.OnItemSelectedListener es una interfaz que tiene 2 métodos obligatorios:
            onItemSelected
            onNothingSelected
        Entonces necesitas crear un "objeto anónimo" (object) que implemente esos 2 métodos.
        ¿Por qué en setOnClickListener no pasa lo mismo?
            Porque View.OnClickListener solo tiene 1 método: onClick(View).
            Cuando una interfaz tiene más de un método (como OnItemSelectedListener), no puede adivinar
                cuál de ellos quieres implementar.
        Por eso NO puede usar una lambda directamente. Necesitas escribir object : y override explícitamente.

        En Kotlin (y en Java también), cuando quieres usar una interfaz que tiene más de un método, no basta con pasarle una lambda.
        Debes crear una instancia de esa interfaz e implementar todos sus métodos.
        Para eso, en Kotlin, se usa:
        object : Interfaz {
            override fun metodo1() {}
            override fun metodo2() {}
}

        */

        binding.spUnidadmedida.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                valorparacalculo = equivalencias[position]
                //Actualizar total?
                actualizarTotal()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se seleccionó nada
            }
        }



    }

    private fun actualizarTextoCantidad() {
        binding.tvCantidad.text = cantidad.toString()
    }
    private fun actualizarTotal() {
        val total = cantidad/valorparacalculo
        //binding.tvTotal.text = "$total"
        val formato = DecimalFormat("#.##")
        binding.tvTotal.text = "${formato.format(total)}"
    }


}