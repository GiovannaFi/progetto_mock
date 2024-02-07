package gio.ado.prova

data class Parent(
    val date: String,
    val dataList: List<Mission>
)

data class Mission(
    val date : String,
    val title : String,
    val numberDocument : String,
    val name : String
)

