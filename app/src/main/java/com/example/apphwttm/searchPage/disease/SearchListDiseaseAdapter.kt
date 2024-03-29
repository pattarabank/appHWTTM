package com.example.apphwttm.searchPage.disease

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.DiseaseSearchModel

class SearchListDiseaseAdapter(var diseaseSearchModelList: List<DiseaseSearchModel>) :
    RecyclerView.Adapter<SearchListDiseaseAdapter.SearchListDiseaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListDiseaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_disease_search, parent, false)
        return SearchListDiseaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchListDiseaseViewHolder, position: Int) {
        holder.bind(diseaseSearchModelList[position])
    }

    override fun getItemCount(): Int {
        return diseaseSearchModelList.size
    }

    class SearchListDiseaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemDescription = ""
        private var itemName = ""
        val isKid = itemView.getContext().getSharedPreferences("isKid", Context.MODE_PRIVATE)
        val getIsKid = isKid.getBoolean("isKid", true)
        fun bind(diseaseSearchModel: DiseaseSearchModel) {
            itemView.findViewById<TextView>(R.id.disease_tv_1).text = diseaseSearchModel.name
            itemName = diseaseSearchModel.name
            itemDescription = if (getIsKid) {
                diseaseSearchModel.des_kid
            } else {
                diseaseSearchModel.des
            }

        }

        init {
            itemView.setOnClickListener { v: View ->
                //Toast.makeText(v.context,itemDetail,Toast.LENGTH_SHORT).show()
                //intent eiei
                val intent = Intent(itemView.context, DetailDiseaseSearch::class.java)
                intent.putExtra("send_to_detail_disease_name", itemName)
                intent.putExtra("send_to_detail_disease_des", itemDescription)

                itemView.context.startActivity(intent)

            }
        }

    }


}