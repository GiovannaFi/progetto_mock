package gio.ado.prova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import gio.ado.prova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val parents = listOf(
        Parent("Section1", listOf("Apple", "Banana", "Cherry")),
        Parent("Section1", listOf("Dog", "Elephant", "Fox")),
        Parent("Section1", listOf("Grapes", "Horse", "Ice Cream")),

        Parent("Section2", listOf("Java", "Kotlin", "Lemon")),
        Parent("Section2", listOf("Monkey", "Nest", "Orange")),

        Parent("Section3", listOf("Pizza", "Quasar", "Rainbow")),
        Parent("Section3", listOf("Strawberry", "Tree", "Umbrella")),

        Parent("Section8", listOf("Vanilla", "Watermelon", "Xylophone"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val sectionedParents = createSectionedParents(parents)

        binding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ParentAdapter(sectionedParents)
        }
    }

    private fun createSectionedParents(parents: List<Parent>): List<Parent> {
        val sectionMap = mutableMapOf<String, MutableList<String>>()

        // Raggruppa gli elementi per sezione
        for (parent in parents) {
            val section = parent.section
            val items = sectionMap.getOrPut(section) { mutableListOf() }
            items.addAll(parent.list)
        }

        // Crea gli oggetti Parent con sezioni e relativi elementi
        return sectionMap.entries.map { (section, items) ->
            Parent(section, items)
        }
    }

}