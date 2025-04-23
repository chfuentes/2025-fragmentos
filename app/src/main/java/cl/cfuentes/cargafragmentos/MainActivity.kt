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
            insertarFragmento(Frag1Fragment())

        }

        binding.btnFragmento2.setOnClickListener {
            /*val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flContainer, Frag2Fragment())
            fragmentTransaction.commit()*/
            insertarFragmento(Frag2Fragment())
        }
    }

    private fun insertarFragmento(fragmento : Fragment) {
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flContainer.id, fragmento)
        fragmentTransaction.commit()
    }
}