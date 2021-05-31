package com.example.basedatos2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception

class IngresarP : AppCompatActivity() {

    var BIngresarp2: Button?=null
    var BVentasp: Button?=null
    var Idp: EditText?=null
    var Nombrep:EditText?=null
    var Disponiblep:EditText?=null
    var Vendidop:EditText?=null
    var Valorup:EditText?=null
    var Valortp:EditText?=null
    var P:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_p)

        BIngresarp2=findViewById(R.id.bingresarp2)
        BVentasp=findViewById(R.id.bventasp)

        Idp=findViewById(R.id.idp)
        Nombrep=findViewById(R.id.nombrep)
        Disponiblep=findViewById(R.id.disponiblep)
        Vendidop=findViewById(R.id.vendidop)
        Valorup=findViewById(R.id.valorup)
        Valortp=findViewById(R.id.valortp)

    }



    override fun onResume() {
        super.onResume()

        BIngresarp2!!.setOnClickListener{

            if (Idp!!.length() == 0) {

                Toast.makeText(this, "CAMPO ID VACIO", Toast.LENGTH_LONG).show()
            }

            else if (Nombrep!!.length() == 0){

                Toast.makeText(this, "CAMPO NOMBRE VACIO", Toast.LENGTH_LONG).show()

            }

            else if (Disponiblep!!.length() == 0){

                Toast.makeText(this, "CAMPO DISPONIBLES VACIO", Toast.LENGTH_LONG).show()

            }

            else if (Vendidop!!.length() == 0){

                Toast.makeText(this, "CAMPO VENDIDOS VACIO", Toast.LENGTH_LONG).show()

            }


            else if (Valorup!!.length() == 0){

                Toast.makeText(this, "CAMPO VALOR UNITARIO VACIO", Toast.LENGTH_LONG).show()

            }

            else if (Valortp!!.length() == 0){

                Toast.makeText(this, "CAMPO VALOR TOTAL VACIO", Toast.LENGTH_LONG).show()
            }


            else{

                    consultarP()
            }

        }

        BVentasp!!.setOnClickListener{

            val r: Intent = Intent(this,Ingresar::class.java)
            startActivity(r)

        }

    }

    private fun ingresarP() {

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.43.143/inventario/ingresarp.php?ID=${Idp!!.text}&NOMBRE=${Nombrep!!.text}&DISPONIBLE=${Disponiblep!!.text}&VENDIDO=${Vendidop!!.text}&VALORU=${Valorup!!.text}&VALORT=${Valortp!!.text}"

        val stringRequest = StringRequest (
            Request.Method.GET, url,

            Response.Listener<String>{ response ->

                if (response.equals("OK", true) ){

                    Toast.makeText(this, "PRODUCTO INGRESADO" , Toast.LENGTH_LONG).show()

                    Idp!!.setText("")
                    Nombrep!!.setText("")
                    Disponiblep!!.setText("")
                    Vendidop!!.setText("")
                    Valorup!!.setText("")
                    Valortp!!.setText("")
                }
                else {
                    Toast.makeText(this, "PRODUCTO NO INGRESADO " , Toast.LENGTH_LONG).show()
                }

            },

            Response.ErrorListener { Toast.makeText(this, "NO HAY CONEXIÓN CON LA BASE DE DATOS" , Toast.LENGTH_LONG).show()})

        queue.add(stringRequest)

    }

    private fun consultarP() {

        val url="http://192.168.43.143/inventario/consultap.php?ID=${Idp!!.text}"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    val data = obj.getJSONArray("datosp")
                    var info: JSONObject = data.getJSONObject(0)

                    P = info.getString("ID")

                    if (P == Idp!!.text.toString()) {

                        Toast.makeText(this, "EL ID YA SE ENCUENTRA REGISTRADO", Toast.LENGTH_LONG).show()

                    }
                }

                catch(e: Exception){
                    ingresarP()
                }
            },
            Response.ErrorListener { Toast.makeText(this,"NO HAY CONEXIÓN CON LA BASE DE DATOS", Toast.LENGTH_LONG).show() })
        queue.add(stringRequest)

    }



}
