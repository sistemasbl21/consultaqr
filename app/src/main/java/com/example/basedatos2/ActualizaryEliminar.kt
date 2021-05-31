package com.example.basedatos2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception

class ActualizaryEliminar : AppCompatActivity() {

    var BBuscar:Button?=null
    var BActualizar2:Button?=null
    var BEliminar:Button?=null
    var Ida2:TextView?=null
    var Nombrea2:TextView?=null
    var Disponiblea2:TextView?=null
    var Vendidoa2:TextView?=null
    var Valorua2:TextView?=null
    var Valorta2:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizary_eliminar)

        BBuscar= findViewById(R.id.bbuscar)
        BActualizar2= findViewById(R.id.bactualizar)
        BEliminar= findViewById(R.id.beliminar)
        Ida2= findViewById(R.id.ida2)
        Nombrea2= findViewById(R.id.nombrea2)
        Disponiblea2= findViewById(R.id.disponiblea2)
        Vendidoa2= findViewById(R.id.vendidoa2)
        Valorua2= findViewById(R.id.valorua2)
        Valorta2= findViewById(R.id.valorta2)

    }


    override fun onResume() {
        super.onResume()

        BBuscar!!.setOnClickListener{


            if (Ida2!!.length() == 0) {

                Toast.makeText(this, "CAMPO ID VACIO", Toast.LENGTH_LONG).show()
            }

            else{

                consultarA()
            }



        }

        BActualizar2!!.setOnClickListener{

            Actualizar()

        }

        BEliminar!!.setOnClickListener{

            Eliminar()

        }




    }

    private fun Actualizar() {

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.43.143/inventario/actualizar.php?ID=${Ida2!!.text}&NOMBRE=${Nombrea2!!.text}&DISPONIBLE=${Disponiblea2!!.text}&VENDIDO=${Vendidoa2!!.text}&VALORU=${Valorua2!!.text}&VALORT=${Valorta2!!.text}"

        val stringRequest = StringRequest (
            Request.Method.GET, url,

            Response.Listener<String>{ response ->

                if (response.equals("OK", true) ){

                    Toast.makeText(this, "PRODUCTO ACTUALIZADO" , Toast.LENGTH_LONG).show()

                    Ida2!!.setText("")
                    Nombrea2!!.setText("")
                    Disponiblea2!!.setText("")
                    Vendidoa2!!.setText("")
                    Valorua2!!.setText("")
                    Valorta2!!.setText("")
                }
                else {
                    Toast.makeText(this, "PRODUCTO NO ACTUALIZADO " , Toast.LENGTH_LONG).show()
                }

            },

            Response.ErrorListener { Toast.makeText(this, "NO HAY CONEXIÓN CON LA BASE DE DATOS" , Toast.LENGTH_LONG).show()})

        queue.add(stringRequest)

    }

    private fun Eliminar() {

        val url="http://192.168.43.143/inventario/eliminar.php?ID=${Ida2!!.text}"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                try {


                    Toast.makeText(this,"DATO  ELIMINADO", Toast.LENGTH_LONG).show()

                    Ida2!!.setText("")
                    Nombrea2!!.setText("")
                    Disponiblea2!!.setText("")
                    Vendidoa2!!.setText("")
                    Valorua2!!.setText("")
                    Valorta2!!.setText("")

                }

                catch(e: Exception){
                    Toast.makeText(this,"DATO NO ELIMINADO", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { Toast.makeText(this,"NO HAY CONEXIÓN CON LA BASE DE DATOS", Toast.LENGTH_LONG).show() })
        queue.add(stringRequest)

    }


    private fun consultarA() {

        val url="http://192.168.43.143/inventario/consultap.php?ID=${Ida2!!.text}"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    val data = obj.getJSONArray("datosp")
                    var info: JSONObject = data.getJSONObject(0)

                    Nombrea2!!.text = info.getString("NOMBRE")
                    Disponiblea2!!.text= info.getString("DISPONIBLE")
                    Vendidoa2!!.text= info.getString("VENDIDO")
                    Valorua2!!.text= info.getString("VALORU")
                    Valorta2!!.text= info.getString("VALORT")

                }

                catch(e: Exception){
                    Toast.makeText(this,"DATO NO ENCONTRADO", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { Toast.makeText(this,"NO HAY CONEXIÓN CON LA BASE DE DATOS", Toast.LENGTH_LONG).show() })
        queue.add(stringRequest)

    }


}
