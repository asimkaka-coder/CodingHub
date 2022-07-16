package com.example.codinghub.auth.data.models

data class User(
    var userID: String? = null,
    var userName: String? = null,
    var fullName: String? = null,
    var imageURL: String? = null,
    var bio: String? = null,
    var following: List<String>? = null,
) {
    fun toMap() = mapOf(
        "userId" to userID,
        "username" to userName,
        "name" to fullName,
        "imageUrl" to imageURL,
        "bio" to bio,
        "following" to following
    )
}