package com.example.apphwttm.admin.manage_firstAid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.searchPage.firstaid.FirstAidModel

class DeleteFirstAidAdapter(var DeleteFirstAidModelList: List<FirstAidModel>) :
    RecyclerView.Adapter<DeleteFirstAidAdapter.DeleteFirstAidViewHolder>() {
    class DeleteFirstAidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName = ""
        private var docId = ""
        fun bind(firstAidModel: FirstAidModel) {
            itemView.findViewById<TextView>(R.id.firstaid_tv_1).text = firstAidModel.name
            itemName = firstAidModel.name
            docId = firstAidModel.documentId
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DeleteFirstAidData2Activity::class.java)
                intent.putExtra("send_to_delete_firstaid_id", docId)
                intent.putExtra("send_to_delete_firstaid_name", itemName)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteFirstAidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_firstaid_search, parent, false)
        return DeleteFirstAidViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeleteFirstAidViewHolder, position: Int) {
        holder.bind(DeleteFirstAidModelList[position])
    }

    override fun getItemCount(): Int {
        return DeleteFirstAidModelList.size
    }
}