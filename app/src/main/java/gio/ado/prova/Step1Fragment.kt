package gio.ado.prova

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import gio.ado.prova.databinding.Step1FragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class Step1Fragment : Fragment() {

    private var baseContainerBinding: Step1FragmentBinding? = null
    private val binding get() = baseContainerBinding!!

    private lateinit var step1Adapter: Step1Adapter


    private val missionList = listOf(
        FakeNetworkObject(
            "11/12/2023",
            "123456  - RITIRO PER RECESSO SENZA ATTIVAZIONE",
            "V235671111",
            "PAOLO NAPOLI"
        ),
        FakeNetworkObject(
            "11/12/2023",
            "123444 - PRIMA ATTIVAZIONE",
            null,
            "LAURA PALERMO"
        ),
        FakeNetworkObject(
            "08/12/2023",
            "55555 - CONSEGNA COMODATO USO",
            "N235671345",
            "CLAUDIO ROMA"
        ),
        FakeNetworkObject("03/11/2023", "111111 - SANIFICAZIONE", "N235671345", "LUCA VERONA"),
        FakeNetworkObject(
            "03/11/2023",
            "221011 - VENDITA PER RISCATTO",
            null,
            "ROBERTA LUCCA"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseContainerBinding = Step1FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        step1Adapter = Step1Adapter(fromNetworkObjToElement(missionList))

        binding.recyclerViewDischargingMissions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = step1Adapter
        }

        binding.fabDischargingMissionsNext.setOnClickListener {
            val selectedMissions = step1Adapter.getSelectedMissionTitles()
            if (selectedMissions.isNotEmpty()) {

                findNavController().navigate(R.id.step2Fragment)
            } else {
                Toast.makeText(requireContext(), "Nessuna missione selezionata", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    private fun fromNetworkObjToElement(listaFake: List<FakeNetworkObject>): List<Element> {
        return listaFake
            .groupBy { networkElement ->
                networkElement.date
            }
            .flatMap { networkElement ->
                listOf<Element>(
                    Date(
                        date = networkElement.key
                    )
                ).plus(
                    fromNetworkObjToMission(networkElement.value)
                )
            }
    }

    private fun fromNetworkObjToMission(networkList: List<FakeNetworkObject>): List<Mission> {
        return networkList.map { networkElement ->
            Mission(
                date = networkElement.date,
                title = networkElement.title,
                numberDocument = networkElement.numberDocument,
                name = networkElement.name,
            )
        }
    }


}