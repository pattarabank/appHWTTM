package com.example.apphwttm.searchPage.disease

import com.google.firebase.firestore.DocumentId

data class DiseaseSearchModel(
    val name: String = "",
    val des: String = "",
    val des_kid: String = "",
    @DocumentId
    val documentId: String = ""
)