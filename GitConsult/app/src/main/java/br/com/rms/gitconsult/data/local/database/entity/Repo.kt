package br.com.rms.gitconsult.data.local.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Repo (
    val id: Int?,
    val name: String?,
    val `private`: Boolean?,
    val created_at: String?,
    val updated_at: String?,
    val forks_count: Int?,
    val stargazers_count: Int?,
    val watchers_count: Int?
    )