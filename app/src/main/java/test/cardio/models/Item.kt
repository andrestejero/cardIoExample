package test.cardio.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val id: Long, val title: String, val url: String) : Parcelable