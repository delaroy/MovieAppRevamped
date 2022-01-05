package com.delaroystudios.movieapp.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
class Movie() : Parcelable {
    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("adult")
    var isAdult = false

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("popularity")
    var popularity: Double? = null

    @SerializedName("vote_count")
    var voteCount: Int? = null

    @SerializedName("video")
    var video: Boolean? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    constructor(parcel: Parcel) : this() {
        posterPath = parcel.readString()
        isAdult = parcel.readByte() != 0.toByte()
        overview = parcel.readString()
        releaseDate = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        originalTitle = parcel.readString()
        originalLanguage = parcel.readString()
        title = parcel.readString()
        backdropPath = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        voteCount = parcel.readValue(Int::class.java.classLoader) as? Int
        video = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterPath)
        parcel.writeByte(if (isAdult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeValue(id)
        parcel.writeString(originalTitle)
        parcel.writeString(originalLanguage)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeValue(popularity)
        parcel.writeValue(voteCount)
        parcel.writeValue(video)
        parcel.writeValue(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}