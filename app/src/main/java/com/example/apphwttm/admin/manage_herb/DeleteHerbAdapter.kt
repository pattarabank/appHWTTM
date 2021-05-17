package com.example.apphwttm.admin.manage_herb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.HerbSearchModel

class DeleteHerbAdapter(var DeleteHerbModelList: List<HerbSearchModel>) :
    RecyclerView.Adapter<DeleteHerbAdapter.DeleteHerbViewHolder>() {
    class DeleteHerbViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName = ""
        private var docId = ""
        fun bind(herbSearchModel: HerbSearchModel) {
            itemView.findViewById<TextView>(R.id.herb_tv_1).text = herbSearchModel.name
            itemName = herbSearchModel.name
            docId = herbSearchModel.documentId
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,DeleteHerbData2Activity::class.java)
                intent.putExtra("send_to_delete_herb_id",docId)
                intent.putExtra("send_to_delete_herb_name",itemName)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteHerbViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_herb_search, parent, false)
        return DeleteHerbViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeleteHerbViewHolder, position: Int) {
        holder.bind(DeleteHerbModelList[position])
    }

    override fun getItemCount(): Int {
        return DeleteHerbModelList.size
    }
}