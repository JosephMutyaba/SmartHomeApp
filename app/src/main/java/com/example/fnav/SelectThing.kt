package com.example.fnav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.fnav.databinding.SelectThingBinding

class SelectThing : AppCompatActivity() {
    private lateinit var selectThing: SelectThingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectThing= SelectThingBinding.inflate(layoutInflater)
        setContentView(selectThing.root)

        val selec=selectThing.containerThing
        val enter_text=layoutInflater.inflate(R.layout.fragment_enter_tex_, null)
        selec.setOnClickListener(){
            selec.removeAllViews()
            selec.addView(enter_text)


            enter_text.findViewById<TextView>(R.id.textEnterNotif).setOnClickListener(){
                val intentbackHome=Intent(this, CreateRoutine::class.java)
                intentbackHome.putExtra("backR","back")
                startActivity(intentbackHome)

            }
        }

        selectThing.arrowBack.setOnClickListener(){
            val intent=Intent(this, CreateRoutine::class.java)
            startActivity(intent)
        }
    }
}