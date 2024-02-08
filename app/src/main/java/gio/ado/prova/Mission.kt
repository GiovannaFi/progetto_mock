package gio.ado.prova

abstract class Element(val id : Int)

data class Mission(
    val date : String,
    val title : String,
    val numberDocument : String? = null,
    val name : String
) : Element(0)


data class Date(
    val date : String
) : Element(1)


data class FakeNetworkObject(
    val date : String,
    val title : String,
    val numberDocument : String? = null,
    val name : String,
)