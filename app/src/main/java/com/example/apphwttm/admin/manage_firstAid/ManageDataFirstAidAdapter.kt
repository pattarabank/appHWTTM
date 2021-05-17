package com.example.apphwttm.admin.manage_firstAid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.FirstAidModel

class ManageDataFirstAidAdapter(var ManageFirstAidModelList: List<FirstAidModel>) :
    RecyclerView.Adapter<ManageDataFirstAidAdapter.ManageDataFirstAidViewHolder>() {
    class ManageDataFirstAidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName = ""
        private var itemDescription = ""
        private var itemKeyword:List<String> = emptyList()
        private var docId = ""
        fun bind(firstAidModel: FirstAidModel) {
            itemView.findViewById<TextView>(R.id.firstaid_tv_1).text = firstAidModel.name
            itemName = firstAidModel.name
            itemDescription = firstAidModel.des
            itemKeyword = firstAidModel.keyword
            docId = firstAidModel.documentId
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EditFirstAidData2Activity::class.java)
                intent.putExtra("send_to_detail_firstaid_name", itemName)
                intent.putExtra("send_to_detail_firstaid_des", itemDescription)
                intent.putExtra("send_to_detail_firstaid_keyword", itemKeyword.toString().trim())
                intent.putExtra("send_to_detail_firstaid_id", docId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageDataFirstAidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_firstaid_search, parent, false)
        return ManageDataFirstAidViewHolder(view)
    }

    override fun onBindViewHolder(holder: ManageDataFirstAidViewHolder, position: Int) {
        holder.bind(ManageFirstAidModelList[position])
    }

    override fun getItemCount(): Int {
        return ManageFirstAidModelList.size
    }
}