package com.example.stefano.happypark.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import android.util.Log
import com.example.stefano.happypark.BuildConfig
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat

/**
 * Created by stefano on 13/06/17.
 */
object FileCreator {



       fun getOutputMediaFileUri(mediaTypeImage: Int,context: Context): Uri? {
        if (FileCreator.isExternalStorageAvaiable()) {
            val mediaStorageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            return FileCreator.getUriFromFile(mediaStorageDir, context)
        }
        return null
    }

    fun getUriFromFile(mediaStorageDir: File, context: Context): Uri? {

        var fileName = ""
        var fileType = ""
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(java.util.Date())

        fileName = "IMG_" + timeStamp
        fileType = ".bmp"

        val mediaFile: File
        try {
            mediaFile = File.createTempFile(fileName, fileType, mediaStorageDir)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("St", "Error creating file: " + mediaStorageDir.absolutePath + fileName + fileType)
            return null
        }

        return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", mediaFile)
    }


    fun isExternalStorageAvaiable(): Boolean {
        val state = Environment.getExternalStorageState()

        return Environment.MEDIA_MOUNTED == state

    }
}