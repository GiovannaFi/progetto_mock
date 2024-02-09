package gio.ado.prova

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gio.ado.prova.databinding.DateElementBinding
import gio.ado.prova.databinding.MissionCardBinding

class ParentAdapter(
    private val list: List<Element>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val cardChecked: MutableList<Mission> = mutableListOf()

    inner class MissionViewHolder(private val binding: MissionCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mission: Mission) {
            binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (mission in cardChecked) {
                    cardChecked.remove(mission)  //la card è nella lista, significa che è checkata e quindi la togliamo dalla lista per uncheckarla, posso usarla nel prossimo screen?
                    Log.d("CHECK FALSE?", "title = ${mission.title}, è $isChecked")
                    TransitionManager.beginDelayedTransition(
                        binding.openableCard,
                        AutoTransition()
                    )
                    binding.openableCard.visibility = View.GONE

                } else {   //se non è nella lista la aggiungiamo così si checka
                    cardChecked.add(mission)
                    Log.d("CHECK TRUE?", "title = ${mission.title}, è $isChecked")
                    TransitionManager.beginDelayedTransition(
                        binding.openableCard,
                        AutoTransition()
                    )
                    binding.openableCard.visibility = View.VISIBLE
                }
            }

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

    inner class DateViewHolder(private val viewDataBinding: DateElementBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(data: Date) {
            viewDataBinding.tvTitle.text = data.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> {
                val binding = MissionCardBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                MissionViewHolder(binding)
            }

            1 -> {
                val binding =
                    DateElementBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                DateViewHolder(binding)
            }

            else -> {
                throw Exception("oh no")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sectionedParent = list[position]
        when (holder) {
            is DateViewHolder -> holder.bind(sectionedParent as Date)
            is MissionViewHolder -> holder.bind(sectionedParent as Mission)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].id
    }

    fun getSelectedMissionTitles(): List<String> {
        return cardChecked.map { it.title }
    }
}
