package com.example.androidlabs

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.UUID
import java.util.Date

@Parcelize
@Entity
data class City(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var img: Int? = null,
    var title: String = "",
    var date: Date = Date(),
    var isFavorite: Boolean = false
) : Parcelable
