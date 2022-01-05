package com.delaroystudios.movieapp.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    var quantity: String? = null,
    @NonNull
    @PrimaryKey var value: String

)