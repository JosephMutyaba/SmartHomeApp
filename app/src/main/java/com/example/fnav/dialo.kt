package com.example.fnav

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.fnav.databinding.FragmentSendNotifBinding

class dialo:DialogFragment() {
    private lateinit var send_notification:FragmentSendNotifBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_notif, null)
        dialogView.findViewById<EditText>(R.id.notificationDialogtext)
            .text.toString()

        send_notification= FragmentSendNotifBinding.inflate(layoutInflater)
        val editable_send_notification=send_notification.editableSendNotification.text
        builder.setView(dialogView).setMessage("Enter a value")
            .setPositiveButton("OK"){dialog,id->
                dismiss()
                val intent=Intent(activity,CreateRoutine::class.java)
                intent.putExtra("progBarLauncher",editable_send_notification)
                startActivity(intent)

            }
            .setNegativeButton("CANCEL"){dialog, id->
                dismiss()

            }

        return builder.create()

    }
}