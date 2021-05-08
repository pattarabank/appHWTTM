package com.example.apphwttm.searchPage.firstaid

import com.google.firebase.firestore.DocumentId

data class FirstAidModel(
    val name: String = "",
    val des: String = "",
    @DocumentId
    val documentId: String = ""
)
