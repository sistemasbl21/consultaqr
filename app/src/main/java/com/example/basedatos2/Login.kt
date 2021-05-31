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
import org.json.JSONObject
import java.lang.Exception

class Login : AppCompatActivity() {


    var Blogin: Button?=null
    var Usuario:EditText?=null
    var Pass:EditText?=null
    var P:String?=null
    var C:String?=null
    var N:String?=null
    var CA:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Blogin=findViewById(R.id.blogin)
        Usuario=findViewById(R.id.usuario)
        Pass=findViewById(R.id.pass)

    }


    override fun onResume() {

        super.onResume()


        Blogin!!.setOnClickListener{



            if (Usuario!!.length() == 0) {

                Toast.makeText(this, "CAMPO USUARIO VACIO", Toast.LENGTH_LONG).show()
            }

           else if (Pass!!.length() == 0){

                Toast.makeText(this, "CAMPO CONTRASEÑA VACIO", Toast.LENGTH_LONG).show()

            }

            else{

                consultarL()



            }

        }


    }

    private fun consultarL() {

        val url="http://192.168.1.4/inventario/usuario.php?CEDULA=${Usuario!!.text}"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                try{
                    val obj = JSONObject(response)
                    val data = obj.getJSONArray("usuario")
                    var info: JSONObject =data.getJSONObject(0)

                    C=info.getString("CEDULA")
                    N=info.getString("NOMBRE")
                    P=info.getString("PASSWORD")
                    CA=info.getString("CARGO")

                    if (C.equals("${Usuario!!.text}", true) && P.equals("${Pass!!.text}", true) ){

                        val m: Intent = Intent(this,CAvanzado::class.java)
                        startActivity(m)

                        Usuario!!.setText("")
                        Pass!!.setText("")

                    }
                    else{

                        Toast.makeText(this, "USUARIO O CANTRASEÑA NO VALIDO", Toast.LENGTH_LONG).show()
                    }

                }
                catch(e: Exception){
                    Toast.makeText(this,"USUARIO O CANTRASEÑA NO VALIDO", Toast.LENGTH_LONG).show()
                }

//nomC!!.text=data.getJSONArray(0).toString()

            },
            Response.ErrorListener { Toast.makeText(this,"NO HAY CONEXIÓN CON LA BASE DE DATOS", Toast.LENGTH_LONG).show() })
        queue.add(stringRequest)

    }


}
