package br.com.rms.gitconsult.data.remote.model.repo

data class Permissions(
    val admin: Boolean?,
    val push: Boolean?,
    val pull: Boolean?
)