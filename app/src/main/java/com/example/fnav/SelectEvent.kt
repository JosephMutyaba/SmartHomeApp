package com.example.fnav

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fnav.databinding.SelectEventBinding
import java.util.*

class SelectEvent : AppCompatActivity() {
    private lateinit var selectEvent: SelectEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectEvent= SelectEventBinding.inflate(layoutInflater)
        setContentView(selectEvent.root)

        selectEvent.arrowBack.setOnClickListener(){
            val intent=Intent(this, CreateRoutine::class.java)
            startActivity(intent)
        }

        selectEvent.linear11.setOnClickListener(){
            val intentback=Intent(this, CreateRoutine::class.java)
            intentback.putExtra("hello","MAXIMILLIAN")
            startActivity(intentback)
        }
    }
}

