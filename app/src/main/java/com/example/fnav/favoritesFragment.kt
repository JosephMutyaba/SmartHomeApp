package com.example.fnav

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class favoritesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflated= inflater.inflate(R.layout.fragment_favourites, container, false)
        val floatButton=inflated.findViewById<FloatingActionButton>(R.id.buttonFav)
        floatButton.setOnClickListener(){
            val fintent=Intent(activity,RoutineSelect::class.java)
            startActivity(fintent)
        }
        return inflated
    }



}