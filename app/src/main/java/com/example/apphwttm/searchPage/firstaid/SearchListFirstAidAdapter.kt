package com.example.apphwttm.searchPage.firstaid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R
import com.example.apphwttm.data_model.FirstAidModel

class SearchListFirstAidAdapter(var firstAidModelList: List<FirstAidModel>) :
    RecyclerView.Adapter<SearchListFirstAidAdapter.SearchListFirstAidViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchListFirstAidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_firstaid_search, parent, false)
        return SearchListFirstAidViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchListFirstAidViewHolder, position: Int) {
        holder.bind(firstAidModelList[position])
    }

    override fun getItemCount(): Int {
        return firstAidModelList.size
    }

    class SearchListFirstAidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemName = ""
        private var itemDescription = ""
        fun bind(firstAidModel: FirstAidModel) {
            itemView.findViewById<TextView>(R.id.firstaid_tv_1).text = firstAidModel.name
            itemName = firstAidModel.name
            itemDescription = firstAidModel.des
        }
        init {
            itemView.setOnClickListener { v:View ->
                val intent = Intent(itemView.context,DetailFirstAidSearch::class.java)
                intent.putExtra("send_to_detail_firstaid_name",itemName)
                intent.putExtra("send_to_detail_firstaid_des",itemDescription)
                itemView.context.startActivity(intent)
            }
        }
    }

}