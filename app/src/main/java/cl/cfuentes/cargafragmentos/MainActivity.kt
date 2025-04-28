package cl.cfuentes.cargafragmentos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import cl.cfuentes.cargafragmentos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        /*val view = binding.root
        setContentView(view)*/
        setContentView(binding.root)

        fragmentManager = supportFragmentManager

        binding.btnFragmento1.setOnClickListener {
            /*val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flContainer, Frag1Fragment())
            fragmentTransaction.commit()*/
            insertarFragmento(Frag1Fragment.newInstance("Christian"))

        }

        binding.btnFragmento2.setOnClickListener {
            /*val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flContainer, Frag2Fragment())
            fragmentTransaction.commit()*/
            insertarFragmento(Frag2Fragment())
        }

        binding.btnFragmento3.setOnClickListener {
            insertarFragmento(Frag3Fragment())
        }
    }

    private fun insertarFragmento(fragmento : Fragment) {
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flContainer.id, fragmento)
        fragmentTransaction.commit()
    }

    /*
    En Android, los parámetros se pasan a través de un Bundle.
    Un Bundle es como un "diccionario" donde puedes guardar datos clave-valor.

    Normalmente, lo hacemos creando una instancia del fragmento usando un método newInstance.
    Adaptar Frag1Fragment para que acepte parámetros
    Agrega un companion object para crear el fragmento con argumentos:

     */
}