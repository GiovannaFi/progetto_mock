package gio.ado.prova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import gio.ado.prova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        binding.recyclerViewDischargingMissions.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ParentAdapter(fromNetworkObjToElement(missionList))
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