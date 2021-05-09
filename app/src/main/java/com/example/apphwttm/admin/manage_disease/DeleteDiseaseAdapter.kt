package com.example.apphwttm.admin.manage_disease

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.searchPage.disease.DiseaseSearchModel

class DeleteDiseaseAdapter(var DeleteDiseaseModelList: List<DiseaseSearchModel>) :
    RecyclerView.Adapter<DeleteDiseaseAdapter.DeleteDiseaseViewHolder>() {
    class DeleteDiseaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName = ""
        private var docId = ""
        fun bind(diseaseSearchModel: DiseaseSearchModel) {
            itemView.findViewById<TextView>(R.id.disease_tv_1).text = diseaseSearchModel.name
            itemName = diseaseSearchModel.name
            docId = diseaseSearchModel.documentId
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DeleteDiseaseData2Activity::class.java)
                intent.putExtra("send_to_delete_disease_id", docId)
                intent.putExtra("send_to_delete_disease_name", itemName)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteDiseaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_disease_search, parent, false)
        return DeleteDiseaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeleteDiseaseViewHolder, position: Int) {
        holder.bind(DeleteDiseaseModelList[position])
    }

    override fun getItemCount(): Int {
        return DeleteDiseaseModelList.size
    }
}