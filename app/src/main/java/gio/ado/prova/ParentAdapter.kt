package gio.ado.prova

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gio.ado.prova.databinding.ParentRecyclerViewBinding

class ParentAdapter(private val list: List<Parent>) :
    RecyclerView.Adapter<ParentAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewDataBinding: ParentRecyclerViewBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentAdapter.MyViewHolder {
        val binding = ParentRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentAdapter.MyViewHolder, position: Int) {
        val sectionedParent = list[position]

        holder.viewDataBinding.section.text = sectionedParent.section

        holder.viewDataBinding.childRecyclerView.apply {
            adapter = ChildAdapter(sectionedParent.list)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}