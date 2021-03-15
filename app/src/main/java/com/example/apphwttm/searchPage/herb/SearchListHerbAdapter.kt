package com.example.apphwttm.searchPage.herb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphwttm.R

class SearchListHerbAdapter(var herbSearchModelList: List<HerbSearchModel>) :
    RecyclerView.Adapter<SearchListHerbAdapter.SearchListHerbViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListHerbViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_herb_search, parent, false)
        return SearchListHerbViewHolder(view)
    }


    override fun onBindViewHolder(holder: SearchListHerbViewHolder, position: Int) {
        holder.bind(herbSearchModelList[position])
    }

    override fun getItemCount(): Int {
        return herbSearchModelList.size
    }

    class SearchListHerbViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(herbSearchModel: HerbSearchModel) {
            itemView.findViewById<TextView>(R.id.herb_tv_1).text = herbSearchModel.name
        }
    }

}