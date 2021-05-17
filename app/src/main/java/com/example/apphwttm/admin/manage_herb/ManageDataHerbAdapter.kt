package com.example.apphwttm.admin.manage_herb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.HerbSearchModel

class ManageDataHerbAdapter(var ManageHerbModelList: List<HerbSearchModel>) :
    RecyclerView.Adapter<ManageDataHerbAdapter.ManageHerbDataViewHolder>() {
    class ManageHerbDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName = ""
        private var itemDescription = ""
        private var itemKeyword:List<String> = emptyList()
        private var docId = ""
        fun bind(herbSearchModel: HerbSearchModel) {
            itemView.findViewById<TextView>(R.id.herb_tv_1).text = herbSearchModel.name
            itemName = herbSearchModel.name
            itemDescription = herbSearchModel.des
            itemKeyword = herbSearchModel.keyword
            docId = herbSearchModel.documentId
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EditHerbData2Activity::class.java)
                intent.putExtra("send_to_detail_herb_name", itemName)
                intent.putExtra("send_to_detail_herb_des", itemDescription)
                intent.putExtra("send_to_detail_herb_keyword", itemKeyword.toString().trim())
                intent.putExtra("send_to_detail_herb_id", docId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageHerbDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_herb_search, parent, false)
        return ManageHerbDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ManageHerbDataViewHolder, position: Int) {
        holder.bind(ManageHerbModelList[position])
    }

    override fun getItemCount(): Int {
        return ManageHerbModelList.size
    }

}