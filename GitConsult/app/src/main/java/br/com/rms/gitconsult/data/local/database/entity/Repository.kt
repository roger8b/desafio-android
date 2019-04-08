package br.com.rms.gitconsult.data.local.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Repository (
    var id: Int? = 0,
    var name: String? = "",
    var `private`: Boolean?= false,
    var created_at: String? = "",
    var updated_at: String? = "",
    var forks_count: Int? = 0,
    var stargazers_count: Int? = 0,
    var watchers_count: Int? = 0,
    var description: String? = "",
    var language: String? = ""
    )