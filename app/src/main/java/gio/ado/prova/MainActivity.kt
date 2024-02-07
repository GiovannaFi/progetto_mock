package gio.ado.prova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import gio.ado.prova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val missionList = listOf(
        Mission("11/12/2023", "123456  - RITIRO PER RECESSO SENZA ATTIVAZIONE", "V235671111", "PAOLO NAPOLI"),
        Mission("11/12/2023", "123444 - PRIMA ATTIVAZIONE", "V235671111", "LAURA PALERMO"),
        Mission("08/12/2023", "55555 - CONSEGNA COMODATO USO", "N235671345", "CLAUDIO ROMA"),
        Mission("03/11/2023", "111111 - SANIFICAZIONE", "N235671345", "LUCA VERONA"),
        Mission("03/11/2023", "221011 - VENDITA PER RISCATTO", "N235670000", "ROBERTA LUCCA")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val sectionedParents = mapMissionToParent(missionList)

        binding.recyclerViewDischargingMissions.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ParentAdapter(sectionedParents)
        }
    }

    // accetto una lista di missioni e restituisco una lista di oggetti Parent
    private fun mapMissionToParent(missionList: List<Mission>): List<Parent> {
        // per associare ogni sezione (basata sulla data) a una lista di missioni corrispondenti
        val sectionToMissionMap = mutableMapOf<String, MutableList<Mission>>()

        // scorro attraverso ogni missione nella lista fornita
        for (mission in missionList) {
            // estraggo la sezione dalla data della missione
            val section = mission.date

            // verifico se la data è già presente nella mappa
            if (sectionToMissionMap.containsKey(section)) {
                // se è già presente, aggiungo la missione alla lista esistente
                sectionToMissionMap[section]?.add(mission)
            } else {
                // se non è presente, creo una nuova lista con questa missione
                sectionToMissionMap[section] = mutableListOf(mission)
            }
        }

        // lista per contenere gli oggetti Parent risultanti
        val parentList = mutableListOf<Parent>()

        // scorro attraverso la mappa delle sezioni e missioni
        for ((section, missionDataList) in sectionToMissionMap) {
            // creo un oggetto Parent utilizzando la sezione e la lista di missioni corrispondenti
            val parent = Parent(section, missionDataList)

            // aggiungo l'oggetto Parent alla lista risultante
            parentList.add(parent)
        }
        return parentList
    }


}