package com.example.apphwttm.healthRecord

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R

class AreYouOkAdapter(var searchList: List<AreYouOkModel> , var onItemClickListener: OnItemClickListener ) :
    RecyclerView.Adapter<AreYouOkAdapter.SearchListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_disease_search, parent, false)
        return SearchListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(searchList[position],onItemClickListener)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(areYouOkModel: AreYouOkModel , act:OnItemClickListener) {
            itemView.findViewById<TextView>(R.id.disease_tv_1).text = areYouOkModel.name
            itemView.setOnClickListener {
                act.onClick(adapterPosition)
            }
        }
    }
}
interface OnItemClickListener {
    fun onClick(position: Int)
}