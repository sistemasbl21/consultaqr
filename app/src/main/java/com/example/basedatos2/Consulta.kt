package com.example.basedatos2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.basedatos2.consulta.viewmodel.SearchProductViewModel
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception


class Consulta : AppCompatActivity() {


    private lateinit var btnSearch: Button
    private lateinit var BTNQR: Button

    lateinit var txtId : EditText

    private lateinit var nombrer: TextView
    private lateinit var marca: TextView
    private lateinit var valorr: TextView
    private lateinit var estado: TextView
    private lateinit var fin: TextView
    private lateinit var fsal: TextView
    private lateinit var obs: TextView

    private val myViewModel : SearchProductViewModel by viewModel()

    companion object{

        //var TextoConsulta: TextView?=null



    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        btnSearch = findViewById(R.id.bconsultarr)
        BTNQR=findViewById(R.id.bconsultarrqr)

        txtId = findViewById(R.id.idreparacion)
        nombrer=findViewById(R.id.nombrer)
        marca=findViewById(R.id.marcar)
        estado=findViewById(R.id.estador)
        obs=findViewById(R.id.observar)
        valorr=findViewById(R.id.valorr)
        fin=findViewById(R.id.fechair)
        fsal=findViewById(R.id.fechasr)


        btnSearch.setOnClickListener {
            println("## CLIC CONSULTAR")
            myViewModel.getProd(txtId.text.toString())
        }

        myViewModel.listProd.observe(this,{
            println("### LA LISTA ES: $it")
            val data = it[0]
            txtId.setText(data.id)
            nombrer.text = data.name
            valorr.text = data.totValue.toString()
            marca.text = data.marca
            estado.text = data.estadoEquipo
            fin.text = data.fechaIngreso
            fsal.text = data.fechaSalida
            obs.text = data.observaciones
        })

        BTNQR.setOnClickListener {
            initScanner()
        }
    }


    private fun initScanner(){
        IntentIntegrator(this).initiateScan()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
                myViewModel.getProd(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
