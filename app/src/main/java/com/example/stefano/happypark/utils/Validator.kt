package com.example.stefano.happypark.utils


import android.net.ParseException
import android.widget.EditText
import android.telephony.PhoneNumberUtils.isGlobalPhoneNumber
import android.telephony.PhoneNumberUtils
import java.text.SimpleDateFormat
import java.util.regex.Pattern


/**
 * Created by stefano on 13/06/17.
 */
object Validator {


    fun validatePasswordPattern(password: String): Boolean {
        return password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,})".toRegex())

    }

    fun validateEmailPattern(mail: String): Boolean {

        val pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

        val matcher = pattern.matcher(mail)
        return matcher.find()

    }

    fun validateNameSurname(ns: String): Boolean {
        return ns.matches("^[\\p{L} .'-]+$".toRegex())
    }


    fun validateMobilePhone(num: String): Boolean {

         if (num == "") {
            return false
        } else {
             return PhoneNumberUtils.isGlobalPhoneNumber(num)
        }
    }

    fun validateData(data: String): Boolean {
        if (data == "") {
            return false
        } else {
            val format = SimpleDateFormat("dd/MM/yyyy")

            try {
                val date = format.parse(data)
                val confDate = format.parse("01/01/2006")
                return !date.after(confDate)
            } catch (e: ParseException) {
                e.printStackTrace()
                return false
            }

        }

    }


}

