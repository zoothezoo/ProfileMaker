package jp.co.cyberagent.dojo2019.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//dbの情報
@Entity( primaryKeys = ["name","twitter","github","githubImage"])
data class User(
    //@PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "name") var name: String = "a" ,
    @ColumnInfo(name = "twitter") var twitter: String = "a",
    @ColumnInfo(name = "github") var github: String = "a",
    @ColumnInfo(name = "githubImage") var githubImage: String? = "a"
)

