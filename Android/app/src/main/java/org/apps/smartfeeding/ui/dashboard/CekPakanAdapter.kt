package org.apps.smartfeeding.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.apps.smartfeeding.R
import org.apps.smartfeeding.model.CekPakanData
import org.apps.smartfeeding.utils.dateFormat

class CekPakanAdapter(private val dataList: List<CekPakanData>) :
    RecyclerView.Adapter<CekPakanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pakan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: CekPakanData) {
            // Bind data to the views in the item layout
            itemView.findViewById<TextView>(R.id.tv_banyak_pakan).text = "${data.receivedData} cm"
            itemView.findViewById<TextView>(R.id.tv_item_name).text = data.statusKondisi
            itemView.findViewById<TextView>(R.id.tv_date_time).text = data.dateTime
        }
    }
}
