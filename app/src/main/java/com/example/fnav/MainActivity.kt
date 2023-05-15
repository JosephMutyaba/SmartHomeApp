package com.example.fnav

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val crtRoutData=intent.getStringExtra("toMain")

        //test code
        val sharedPref=getSharedPreferences("mPrefs", Context.MODE_PRIVATE)
        val routineName=sharedPref.getString("routeName",null)
        //test code

        loadFragment(favoritesFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favourites -> {
                    loadFragment(favoritesFragment())
                    true
                }
                R.id.things -> {
                    loadFragment(thingsFragment())
                    true
                }
                R.id.ideas -> {
                    loadFragment(Ideas())
                    true
                }
                R.id.settings -> {
                    loadFragment(Settings())
                    true
                }
                R.id.routines -> {
                    if (routineName!=null){
                        loadFragment(return_routine_Fragment())
                        val returnRoutine=layoutInflater.inflate(R.layout.fragment_return_routine_, null)
                        val newRouName=returnRoutine.findViewById<TextView>(R.id.routineName)
                        newRouName.setText("$routineName")
                    }else{
                        loadFragment(routines())
                    }
                    true
                }
                else->{

                }

            }
            true
        }


        //
        if (routineName!=null){
            binding.bottomNav.menu.findItem(R.id.routines).isChecked=true
            loadFragment(return_routine_Fragment())

            val returnRoutine=layoutInflater.inflate(R.layout.fragment_return_routine_, null)
            val newRouName=returnRoutine.findViewById<TextView>(R.id.routineName)
            newRouName.setText("$routineName")

        }


    }
    private fun loadFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}

