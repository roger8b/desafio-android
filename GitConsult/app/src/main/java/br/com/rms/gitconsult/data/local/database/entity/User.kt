package br.com.rms.gitconsult.data.local.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class User(
    var id: Int? = 0,
    var login: String? = "",
    var name: String? = "",
    var email: String? = "",
    var public_repos: Int? = 0,
    var public_gists: Int? = 0,
    var followers: Int? = 0,
    var following: Int? =0
    )