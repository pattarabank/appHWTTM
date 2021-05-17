package com.example.apphwttm.data_model

import com.google.firebase.firestore.DocumentId

data class FirstAidModel(
    val name: String = "",
    val des: String = "",
    val keyword:List<String> = emptyList(),
    @DocumentId
    val documentId: String = ""
)
