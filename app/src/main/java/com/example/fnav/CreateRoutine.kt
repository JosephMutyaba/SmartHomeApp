package com.example.fnav

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fnav.databinding.CreateRoutineBinding
import java.util.*

class CreateRoutine : AppCompatActivity() {
    private lateinit var createRoutine: CreateRoutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createRoutine= CreateRoutineBinding.inflate(layoutInflater)
        setContentView(createRoutine.root)

        createRoutine.XCancel.setOnClickListener(){
            val intentback=Intent(this,RoutineSelect::class.java)
            startActivity(intentback)
        }

        createRoutine.addEvenBtn.setOnClickListener(){

            //test code
            val editTextRoutineName=createRoutine.editTextRoutineName.text.toString()
            val sharedPref=getSharedPreferences("mPrefs", Context.MODE_PRIVATE)
            val editor=sharedPref.edit()
            editor.putString("routeName",editTextRoutineName)
            editor.apply()
            //test code

            val intentAddEve=Intent(this,SelectEvent::class.java)
            startActivity(intentAddEve)
        }

        createRoutine.actBtn.setOnClickListener(){
            val intentAddAc=Intent(this,SelectThing::class.java)
            startActivity(intentAddAc)
        }

        createRoutine.editTextRoutineName.setOnClickListener(){
            val textInvisible=createRoutine.textInvisible
            textInvisible.visibility=View.VISIBLE
        }

//        createRoutine.VTick.setOnClickListener(){
//            val editTextRoutineName=createRoutine.editTextRoutineName.text.toString()
//            val sharedPref=getSharedPreferences("mPrefs", Context.MODE_PRIVATE)
//            val editor=sharedPref.edit()
//            editor.putString("routeName",editTextRoutineName)
//            editor.apply()
//        }

        //time picker dialog
        val myData1:String? = intent.getStringExtra("hello")
        if (myData1 != null){

            var currenttime= Calendar.getInstance()
            var hour=currenttime.get(Calendar.HOUR_OF_DAY)
            var minute=currenttime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(){ view, hour, minutes->
                val clockFra=layoutInflater.inflate(R.layout.fragment_clock_fra, null)

                val linear=createRoutine.replaceableTime
                val AddEventText=createRoutine.AddEventText
                linear.removeView(AddEventText)
                linear.addView(clockFra)

                clockFra.findViewById<TextView>(R.id.textNewClock).setText("The time is $hour:$minutes")

            }, hour, minute, true).show()

        }

        ////Enter a value dialog
        val myData2:String? = intent.getStringExtra("backR")
        if (myData2!=null){
            val dialog=dialo()
            dialog.show(supportFragmentManager, "my_dialog_tag")
        }

        // progressBar
        val myData3:String? = intent.getStringExtra("progBarLauncher")
        if (myData3!=null){
            val sendNotifFra=layoutInflater.inflate(R.layout.fragment_send_notif, null)
            val replaceable=createRoutine.textAddAc
            val holder=createRoutine.linearAboveAdd

            holder.removeView(replaceable)
            holder.addView(sendNotifFra)

            val replaceabletext=sendNotifFra.findViewById<TextView>(R.id.editableSendNotification)
            replaceabletext.text = "Send Notification:$myData3"

            val dialog2 = Dialog(this)
            dialog2.setContentView(R.layout.indeterminate_bar)
            dialog2.show()

            val handler= Handler(Looper.getMainLooper())
            handler.postDelayed({
                dialog2.dismiss()
                val intent_toMain=Intent(this,MainActivity::class.java)
                intent_toMain.putExtra("toMain","main")
                startActivity(intent_toMain)

            },3000)

        }
    }

}