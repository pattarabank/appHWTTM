package com.example.apphwttm.admin.manage_firstAid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.searchPage.firstaid.FirstAidModel

class ManageDataFirstAidAdapter(var ManageFirstAidModelList: List<FirstAidModel>) :
    RecyclerView.Adapter<ManageDataFirstAidAdapter.ManageDataFirstAidViewHoler>() {
    class ManageDataFirstAidViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var docId = ""
        fun bind(firstAidModel: FirstAidModel) {
            itemView.findViewById<TextView>(R.id.firstaid_tv_1).text = firstAidModel.name
            docId = firstAidModel.documentId
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EditFirstAidData2Activity::class.java)
                intent.putExtra("send_to_detail_firstaid_id", docId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageDataFirstAidViewHoler {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_firstaid_search, parent, false)
        return ManageDataFirstAidViewHoler(view)
    }

    override fun onBindViewHolder(holder: ManageDataFirstAidViewHoler, position: Int) {
        holder.bind(ManageFirstAidModelList[position])
    }

    override fun getItemCount(): Int {
        return ManageFirstAidModelList.size
    }
}