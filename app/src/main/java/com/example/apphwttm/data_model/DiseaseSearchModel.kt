package com.example.apphwttm.data_model

import com.google.firebase.firestore.DocumentId

data class DiseaseSearchModel(
    val name: String = "",
    val des: String = "",
    val des_kid: String = "",
    val keyword:List<String> = emptyList(),
    @DocumentId
    val documentId: String = ""
)