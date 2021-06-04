package com.example.apphwttm.searchPage.disease

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.DiseaseSearchModel

class NewSearchListDiseaseAdapter(var diseaseSearchModelList: List<DiseaseSearchModel>) :
    RecyclerView.Adapter<NewSearchListDiseaseAdapter.NewSearchListDiseaseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewSearchListDiseaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_disease_search, parent, false)
        return NewSearchListDiseaseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NewSearchListDiseaseViewHolder,
        position: Int
    ) {
        holder.bind(diseaseSearchModelList[position])
    }

    override fun getItemCount(): Int {
        return diseaseSearchModelList.size
    }

    class NewSearchListDiseaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemDescription = ""
        private var itemDescriptionKid = ""
        private var itemName = ""
        private var itemId = ""
        fun bind(diseaseSearchModel: DiseaseSearchModel) {
            itemView.findViewById<TextView>(R.id.disease_tv_1).text =
                diseaseSearchModel.name //from single item disease search
            itemName = diseaseSearchModel.name
            itemDescription = diseaseSearchModel.des
            itemDescriptionKid = diseaseSearchModel.des_kid
            itemId = diseaseSearchModel.documentId
        }

        init {
            itemView.setOnClickListener { v: View ->
                val intent = Intent(itemView.context, NewDetailDiseaseSearchActivity::class.java)
                intent.putExtra("send_to_detail_disease_name", itemName)
                intent.putExtra("send_to_detail_disease_des", itemDescription)
                intent.putExtra("send_to_detail_disease_des_kid", itemDescriptionKid)
                intent.putExtra("send_to_detail_disease_id", itemId)
                itemView.context.startActivity(intent)

            }
        }
    }

}