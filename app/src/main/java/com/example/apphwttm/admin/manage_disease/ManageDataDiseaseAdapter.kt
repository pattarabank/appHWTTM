package com.example.apphwttm.admin.manage_disease

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.DiseaseSearchModel

class ManageDataDiseaseAdapter(var ManageDiseaseMedelList: List<DiseaseSearchModel>) :
    RecyclerView.Adapter<ManageDataDiseaseAdapter.ManageDataDiseaseViewHolder>() {
    class ManageDataDiseaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName = ""
        private var itemDes = ""
        private var itemDes_kid = ""
        private var itemKeyword: List<String> = emptyList()
        private var docId = ""
        fun bind(diseaseSearchModel: DiseaseSearchModel) {
            itemView.findViewById<TextView>(R.id.disease_tv_1).text = diseaseSearchModel.name
            itemName = diseaseSearchModel.name
            itemDes = diseaseSearchModel.des
            itemDes_kid = diseaseSearchModel.des_kid
            itemKeyword = diseaseSearchModel.keyword
            docId = diseaseSearchModel.documentId
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EditDiseaseData2Activity::class.java)
                intent.putExtra("send_to_detail_disease_name", itemName)
                intent.putExtra("send_to_detail_disease_des", itemDes)
                intent.putExtra("send_to_detail_disease_des_kid", itemDes_kid)
                intent.putExtra("send_to_detail_disease_keyword", itemKeyword.toString().trim())
                intent.putExtra("send_to_detail_disease_id", docId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageDataDiseaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_disease_search, parent, false)
        return ManageDataDiseaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ManageDataDiseaseViewHolder, position: Int) {
        holder.bind(ManageDiseaseMedelList[position])
    }

    override fun getItemCount(): Int {
        return ManageDiseaseMedelList.size
    }
}