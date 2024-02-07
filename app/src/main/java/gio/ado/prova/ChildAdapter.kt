package gio.ado.prova

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gio.ado.prova.databinding.ChildRecyclerViewBinding

class ChildAdapter(private val list: List<String>) :
    RecyclerView.Adapter<ChildAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewDataBinding: ChildRecyclerViewBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildAdapter.MyViewHolder {
        val binding =
            ChildRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildAdapter.MyViewHolder, position: Int) {
        holder.viewDataBinding.colli2.text = list[position]
    }

    override fun getItemCount(): Int {
       return list.size
    }
}