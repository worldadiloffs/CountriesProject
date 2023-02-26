package uz.itschool

import android.os.Parcelable

data class User(
    var name: String,
    var population: String,
    var area: String,
    var img: String,
    var images: String,
    var images2: String,
    var images3: String
) : java.io.Serializable
