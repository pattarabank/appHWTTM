package com.example.apphwttm.searchPage.herb

import com.google.firebase.firestore.DocumentId

data class HerbSearchModel(
    val name: String = "",
    val des: String = "",
    @DocumentId
    val documentId: String = ""
)