package com.example.apphwttm.searchPage.disease

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.google.android.material.snackbar.Snackbar

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
        private var itemDetail = ""
        fun bind(diseaseSearchModel: DiseaseSearchModel) {
            itemView.findViewById<TextView>(R.id.disease_tv_1).text = diseaseSearchModel.name
             itemDetail = diseaseSearchModel.des
        }
        init {
            itemView.setOnClickListener { v:View ->
                Toast.makeText(v.context,itemDetail,Toast.LENGTH_SHORT).show()
            }
        }

    }


}