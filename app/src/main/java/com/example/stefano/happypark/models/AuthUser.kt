package com.example.stefano.happypark.models

/**
 * Created by stefano on 09/06/17.
 */
class AuthUser(private var mail: String?, private var displayName: String?) {

    fun getMail():String?{
        return mail
    }


    fun getName():String?{
        return displayName
    }

    fun setMail(mail:String?){
        this.mail = mail
    }

    fun setName(name:String?){
        this.displayName = name
    }





}