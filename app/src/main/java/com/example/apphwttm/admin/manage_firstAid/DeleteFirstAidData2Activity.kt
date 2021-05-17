package com.example.apphwttm.admin.manage_firstAid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apphwttm.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DeleteFirstAidData2Activity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "ADDDATATOFIRESTORE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_first_aid_data2)

        var bundle: Bundle? = intent.extras
        var docId: String = bundle!!.getString("send_to_delete_firstaid_id").toString()
        var itemName: String = bundle!!.getString("send_to_delete_firstaid_name").toString()

        MaterialAlertDialogBuilder(this)
            .setTitle("ลบการปฐมพยาบาลเบื้องต้น")
            .setMessage("ต้องการลบ $itemName หรือไม่")
            .setNeutralButton("ยกเลิก") { dialog, which ->
                dialog.cancel()
                finish()
            }
            .setPositiveButton("ตกลง") { dialog, which ->
                deleteDataInFireStore(docId)
                dialog.cancel()
                finish()
            }
            .show()

    }

    private fun deleteDataInFireStore(docId: String) {
        firebaseFirestore.collection("testCollection").document(docId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            firebaseAuth.signInAnonymously().addOnCompleteListener() {
                if (!it.isSuccessful) {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        }
    }

}