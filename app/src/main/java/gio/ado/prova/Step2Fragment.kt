package gio.ado.prova

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gio.ado.prova.databinding.Step2FragmentBinding

class Step2Fragment : Fragment() {
    private var baseContainerBinding: Step2FragmentBinding? = null

    private val binding get() = baseContainerBinding!!
    private lateinit var step2Adapter: Step2Adapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using the generated binding class
        baseContainerBinding = Step2FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

// TODO= MI SERVE LA LISTA

//        step2Adapter = Step2Adapter()
//        binding.recycler.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = step2Adapter
//        }


    }
}
