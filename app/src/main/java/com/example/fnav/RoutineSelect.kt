package com.example.fnav

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fnav.databinding.ActivityMainBinding
import com.example.fnav.databinding.CreateRoutineBinding
import com.example.fnav.databinding.RoutineSelectBinding
import com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RoutineSelect : AppCompatActivity() {
    private lateinit var selectRoutine:RoutineSelectBinding
    private lateinit var ac_main:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectRoutine=RoutineSelectBinding.inflate(layoutInflater)
        setContentView(selectRoutine.root)

        //test code
        val sharedPref=getSharedPreferences("mPrefs", Context.MODE_PRIVATE)
        val routine_Name=sharedPref.getString("routeName",null)
        //test code

        //back to former screen
        val back_arrow=selectRoutine.backFav
        back_arrow.setOnClickListener(){
            ac_main=ActivityMainBinding.inflate(layoutInflater)
            setContentView(ac_main.root)

            loadFragment(favoritesFragment())

            ac_main.bottomNav.setOnItemSelectedListener {
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
                        if (routine_Name!=null){
                            loadFragment(return_routine_Fragment())
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

        }

        findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener(){
            val intentCrRoutine=Intent(this@RoutineSelect,CreateRoutine::class.java)
            startActivity(intentCrRoutine)
        }


        val routinesList=layoutInflater.inflate(R.layout.fragment_routines_list,null)
        if (routine_Name!=null){
            val verticalLinear=selectRoutine.verticalLinear
            verticalLinear.addView(routinesList)
            var tVRouName=routinesList.findViewById<TextView>(R.id.tVRouName)
            tVRouName.text = "$routine_Name"

        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}