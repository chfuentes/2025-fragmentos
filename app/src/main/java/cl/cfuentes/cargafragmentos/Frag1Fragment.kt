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
    /*
    ? = null: empieza como nulo porque se inicializa más tarde.
    !!: forzamos el acceso sabiendo que no es null.
    */
    //Este bloque sirve para manejar el binding (vínculo) entre tu Fragment y su diseño XML usando ViewBinding.
    /*
    ¿Por qué el guion bajo (_binding)?
        Se usa como convención para indicar que esa variable es interna (privada) y modificable.
        No es obligatorio, pero ayuda a diferenciar entre la variable "cruda" (_binding) y la forma segura de acceder a ella (binding).
    ¿Por qué ? = null?
        Se declara como nullable (?) porque:
        El binding no está disponible inmediatamente al crear el fragmento.
        Solo se inicializa cuando se infla la vista (onCreateView), y se debe poner en null
        cuando se destruye para evitar pérdidas de memoria (memory leaks).
    ¿Por qué se usan !! (doble exclamación)?
        El !! le dice a Kotlin: "Confía en mí, esta variable no es nula ahora".
        Aquí se usa porque sabemos que binding ya fue inicializado correctamente,
        y queremos acceder a él sin comprobar si es nulo cada vez.
        ⚠️ Si binding es null en ese momento, el programa crasheará. Por eso se debe usar con cuidado.
    */
    private var _binding : FragmentFrag1Binding?=null
    private val binding get() = _binding!!

    //Pasar Parametros
    private var nombre: String? = null // Aquí guardaremos el parámetro recibido
    //Crear Companion
    companion object {
        private const val ARG_NOMBRE = "nombre"
        //Definir metodo para instancia nueva (Constructor)
        /*¿Por qué hacer esto (usar newInstance)?
        Es recomendado por Android porque:
        Evita errores si el sistema recrea tu fragmento (por ejemplo, en rotación de pantalla).
        Guarda los datos en el Bundle, que Android maneja automáticamente.
        Y mantiene tu fragmento limpio y seguro.*/
        fun newInstance(nombre: String): Frag1Fragment {
            val fragment = Frag1Fragment()
            val args = Bundle()
            args.putString(ARG_NOMBRE, nombre)
            //Aca puedo agregar mas elementos con put
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nombre = arguments?.getString(ARG_NOMBRE) // Recuperamos el parámetro
        /*
        si quiero agregar mas elementos lo debo indicar aca
        arguments?.let {
            nombre = it.getString(ARG_NOMBRE)
            edad = it.getInt(ARG_EDAD)
        }
         */
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Explicar esto
        // return inflater.inflate(R.layout.fragment_frag3, container, false)
        // val view = inflater.inflate(R.layout.fragment_frag3, container, false)
        // medidas = view.findViewById(R.id.sp_unidadmedida)

        /*
        Aquí estamos creando el binding para acceder a los elementos del layout del fragment.
            FragmentFrag1Binding.inflate(...)
            Esta función infla (convierte de XML a vista real en memoria) el layout del fragmento.
            FragmentFrag1Binding es generado automáticamente por ViewBinding a partir del XML fragment_frag1.xml.
        ¿De dónde sale inflater?
            El inflater es un parámetro del méthodo onCreateView:
            Se usa para inflar el layout XML.
         */
        _binding = FragmentFrag1Binding.inflate(inflater,container,false)
        binding.btnFrag1Saludo.setOnClickListener {
            saludame()
        }
        /*
        ¿Qué significa binding.root?
            binding.root devuelve la vista raíz (root view) del layout del fragmento.
            Es lo que se mostrará en pantalla.
            binding contiene referencias a todos los elementos del layout (botones, textos, etc.),
            y root es el contenedor principal de todos ellos.
         */
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saludame(){
        //Toast.makeText(context,"Click en Fragmento",Toast.LENGTH_LONG).show()
        val mensaje = nombre ?: "Click en Fragmento"
        //val mensaje = "Hola, soy $nombre y tengo $edad años."
        Snackbar.make(binding.btnFrag1Saludo,mensaje,Snackbar.LENGTH_SHORT).show()
    }

    //Buscar SafeArgs
}