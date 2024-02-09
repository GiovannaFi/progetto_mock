package gio.ado.prova

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gio.ado.prova.databinding.MissionCardBinding

class Step2Adapter(
    private val list: List<Mission>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MissionViewHolder(private val binding: MissionCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mission: Mission) {
            binding.arrowUp.setOnClickListener {
                TransitionManager.beginDelayedTransition(
                    binding.openableCard,
                    AutoTransition()
                )
                binding.openableCard.visibility = View.GONE
            }
            binding.mission = mission
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = MissionCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MissionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sectionedParent = list[position]
        when (holder) {
            is MissionViewHolder -> holder.bind(sectionedParent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].id
    }
}
