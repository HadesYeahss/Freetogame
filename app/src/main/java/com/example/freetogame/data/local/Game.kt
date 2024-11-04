package com.example.freetogame.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * @author Rigoberto Torres on 02/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@Entity (tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val title: String = "",
    val thumbnail: String = "",
    @SerializedName("short_description")
    val shortDescription: String = "",
    @SerializedName("game_url")
    val gameUrl: String = "",
    val genre: String = "",
    val platform: String = "",
    val publisher: String = "",
    val developer: String = "",
    @SerializedName("release_date")
    val releaseDate: Date? = null,
    @SerializedName("freetogame_profile_url")
    val freeToGameProfileUrl: String = "",
    val isDeleted: Boolean = false
)
