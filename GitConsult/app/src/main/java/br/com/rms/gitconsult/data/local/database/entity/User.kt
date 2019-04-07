package br.com.rms.gitconsult.data.local.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class User(
    val id: Int?,
    val login: String?,
    val name: String?,
    val email: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Int?,
    val following: Int?
    )