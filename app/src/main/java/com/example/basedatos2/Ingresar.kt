package com.example.basedatos2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception

class Ingresar : AppCompatActivity() {

    var BotonConsultar: Button?=null
    var BotonConsultarQR: Button?=null


    companion object{

        var Id: TextView?=null
        var Nombre: TextView?=null
        var ValorU: TextView?=null
        var Disponible: TextView?=null
        var Vendidos: TextView?=null
        var Total: TextView?=null

        var n:Int?=null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)

        BotonConsultar=findViewById(R.id.bconsultarp)
        BotonConsultarQR=findViewById(R.id.bconsultarpqr)
        Id=findViewById(R.id.idproductos)
        Nombre=findViewById(R.id.nombrep)
        ValorU=findViewById(R.id.valorunitariop)
        Disponible=findViewById(R.id.vendidop)
        Vendidos=findViewById(R.id.vendidosp)
        Total=findViewById(R.id.totalp)
    }

    override fun onResume() {

        super.onResume()

        BotonConsultar!!.setOnClickListener{


            if (Id!!.length() == 0) {

                Toast.makeText(this, "CAMPO ID VACIO", Toast.LENGTH_LONG).show()
            }

            else{
                consultar()

            }

        }

        BotonConsultarQR!!.setOnClickListener{

            val j: Intent = Intent(this,QR::class.java)
            startActivity(j)

            n=1

        }


    }

    private fun consultar() {

        val url="http://192.168.1.4/inventario/consultap.php?ID=${Id!!.text}"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Listener<String> { response ->
                try{
                    val obj = JSONObject(response)
                    val data = obj.getJSONArray("datosp")
                    var info: JSONObject =data.getJSONObject(0)

                    //Id!!.text=info.getString("ID")
                    Nombre!!.text=info.getString("NOMBRE")
                    Disponible!!.text=info.getString("DISPONIBLE")
                    Vendidos!!.text=info.getString("VENDIDO")
                    ValorU!!.text=info.getString("VALORU")
                    Total!!.text=info.getString("VALORT")


                }
                catch(e: Exception){
                    Toast.makeText(this,"No existe el dato", Toast.LENGTH_LONG).show()
                }

//nomC!!.text=data.getJSONArray(0).toString()

            },
            Response.ErrorListener { Toast.makeText(this,"Revise la conexión", Toast.LENGTH_LONG).show() })
        queue.add(stringRequest)

    }




}
