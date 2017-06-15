package com.example.stefano.happypark.utils

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import com.example.stefano.happypark.R


/**
 * Created by stefano on 12/06/17.
 */
object AlertMessage {

    fun newAlertErrorMessage(message: String?, context: Context): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("Ok", listener())
    }

    fun listener(): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialog, which -> dialog.dismiss()  }
    }

    fun newAlertMessage(title: String, message: String, context: Context): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", listener())
    }


    fun newAlertMessageListener(title: String, message: String, context: Context, listener: DialogInterface.OnClickListener): AlertDialog.Builder {
        return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", listener)
    }


    fun newProgress(message: String, context: Context): ProgressDialog {
        return ProgressDialog(context)
    }

    fun makePhotoDialog(title:String,context: Context, listener: DialogInterface.OnClickListener):AlertDialog.Builder{
        val items = arrayOf<CharSequence>(context.getString(R.string.camera), context.getString(R.string.gallery), context.getString(R.string.indietro))

        return AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(items,listener)
    }
}
