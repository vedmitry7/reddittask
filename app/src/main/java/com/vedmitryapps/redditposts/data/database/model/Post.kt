package com.vedmitryapps.redditposts.data.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vedmitryapps.redditposts.network.response.Children
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Post(
    @PrimaryKey() var id: String,
    @ColumnInfo(name = "title") var title: String? = "",
    @ColumnInfo(name = "image") var image: String? = ""
) : Parcelable {

    companion object {
        fun getInstanceByChildren(item: Children) : Post {
            return Post(id = item.data.id, title = item.data.title, image = item.data.thumbnail)
        }
    }
}
