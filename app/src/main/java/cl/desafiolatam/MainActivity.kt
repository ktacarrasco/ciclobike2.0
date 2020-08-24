package cl.desafiolatam

import ListaAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_view.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    var lista: ArrayList<Ciclovia>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_filtrar = findViewById<Button>(R.id.button_filtrar) as Button
        val button_no_filtrar = findViewById<Button>(R.id.button_no_filtrar) as
                Button
        val lista_ciclovias = findViewById<RecyclerView>(R.id.lista_ciclovias) as
                RecyclerView
        setupCiclovias()
        lista_ciclovias.layoutManager = LinearLayoutManager(this)
        val listaAdapter = ListaAdapter(lista!!.filter { n->n.comuna.equals(spinnerCyclo.selectedItem) } as MutableList<Ciclovia>,
            this)
        lista_ciclovias.adapter = listaAdapter
        listaAdapter.setupData(lista!!)
        listaAdapter.setupData((lista as ArrayList<Ciclovia>))

        var spinnerList = arrayListOf<String>("mostrar todo")
        for(mComuna:Ciclovia in lista!!)
            spinnerList.add(mComuna.comuna)

     spinnerCyclo.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerList)
        val listaAux: ArrayList<Ciclovia>? = ArrayList()
        listaAdapter.setupData((lista as ArrayList<Ciclovia>))
        spinnerCyclo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{


            //fun AdapterView.OnItemSelectedListener(parent: AdapterView<*>,view:View,position: Int, id:Long ) =
             //   Unit

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

        }


        button_filtrar.setOnClickListener {

            val listaAux: ArrayList<Ciclovia>? = ArrayList()
            lista!!.forEach {
                when (it.comuna) {
                    "Las Condes" -> listaAux!!.add(it)
                }
            }
            listaAdapter.setupData(listaAux!!)
        }


        button_no_filtrar.setOnClickListener {
                 listaAdapter.setupData((lista as ArrayList<Ciclovia>)) }
    }

    private fun setupCiclovias() {
        val setupCiclovias = SetupCiclovias()
        lista = setupCiclovias.init()
    }
}
