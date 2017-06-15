package com.example.stefano.happypark.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.stefano.happypark.callbacks.OnFinishedCallback
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Created by stefano on 13/06/17.
 */
object BitmapCompressor {

    fun compressBitmap(original: Bitmap, context: Context): Uri {

        val out = ByteArrayOutputStream()
        original.compress(Bitmap.CompressFormat.JPEG, 100, out)
        val decoded = BitmapFactory.decodeStream(ByteArrayInputStream(out.toByteArray()))
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, decoded, "Title", null)
        Log.d("pathCompress", path)
        val uriPath = Uri.parse(path)

        return uriPath

    }

    fun compressBitmapInBackground(original: Bitmap, context: Context, callback:OnFinishedCallback) {

        Observable.create<Uri> { s ->
            val out = ByteArrayOutputStream()
            original.compress(Bitmap.CompressFormat.JPEG,100,out)
            val compressed = BitmapFactory.decodeStream(ByteArrayInputStream(out.toByteArray()))
            val path = MediaStore.Images.Media.insertImage(context.contentResolver, compressed,"Title",null)
            val uriPath = Uri.parse(path)

            s.onNext(uriPath)
            s.onComplete()

        }.subscribeOn(Schedulers.computation())
         .observeOn(AndroidSchedulers.mainThread())
                .subscribe { uri ->callback.onFinish(uri.toString())  }

    }
}



