package com.example.stefano.happypark.models

/**
 * Created by stefano on 10/06/17.
 */
class User(private var uid: String? = null, private var phone: String? = null, private var photos: String? = null, private var birthday: String? = null, private var email: String? = null, private var cognome: String? = null, private var nome: String? = null) {

    fun getUid(): String? {
        return uid
    }

    fun getNome(): String? {
        return nome
    }

    fun getCognome(): String? {
        return cognome
    }

    fun getEmail(): String? {
        return email
    }

    fun getBirthday(): String? {
        return birthday
    }

    fun getPhotos(): String? {
        return photos
    }


    fun getPhone(): String? {
        return phone
    }

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun setNome(nome: String) {
        this.nome = nome
    }

    fun setCognome(cognome: String) {
        this.cognome = cognome
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setBirthday(birthday: String) {
        this.birthday = birthday
    }

    fun setPhotos(photos: String) {
        this.photos = photos
    }


}