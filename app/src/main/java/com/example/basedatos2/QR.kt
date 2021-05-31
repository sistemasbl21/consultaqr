package com.example.basedatos2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.json.JSONObject
import java.lang.Exception

class QR : AppCompatActivity(), ZXingScannerView.ResultHandler {

    var escaner: ZXingScannerView?=null //permite abrir y cerrar la camara para leer codigo QR

    var a: String? =null

    override fun handleResult(rawResult: Result?) {
       // Consulta.TextoConsulta!!.text=rawResult.toString()//pone en el textView lo que esta en el QR

        if (Ingresar.n==1){
            Ingresar.Id!!.text=rawResult.toString()
            consultarQR1()
            onBackPressed()//atras

        }

        if (Ingresar.n==2){
            //Consulta.id!!.text=rawResult.toString()
            consultarQR2()
            onBackPressed()//atras

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_qr)

        escaner=ZXingScannerView(this)//va primero
        setContentView(escaner)//Inicializar y enviar como contenido (aparece objeto scaner)-va despues del codigo anterior
    }

    override fun onResume() {
        super.onResume()

        escaner!!.setResultHandler(this)//Abrir objeto y lee codigo QR (activar el resultado codigo QR)
        escaner!!.startCamera()//abre  la camara

    }

    override fun onPause() {
        super.onPause()
        escaner!!.stopCamera()
    }

    //consulta ordenada Json

    private fun consultarQR1() {

        val url="http://192.168.43.143/inventario/consultap.php?ID=${Ingresar.Id!!.text}"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                try{
                    val obj = JSONObject(response)
                    val data = obj.getJSONArray("datosp")
                    var info:JSONObject=data.getJSONObject(0)

                    Ingresar.Nombre!!.text=info.getString("NOMBRE")
                    Ingresar.ValorU!!.text=info.getString("VALORUNITARIO")
                    Ingresar.Disponible!!.text=info.getString("DISPONIBLE")
                    Ingresar.Vendidos!!.text=info.getString("VENDIDOS")
                    Ingresar.Total!!.text=info.getString("TOTAL")


                }
                catch(e:Exception){
                    Toast.makeText(this,"No existe el dato", Toast.LENGTH_LONG).show()
                }

//nomC!!.text=data.getJSONArray(0).toString()

            },
            Response.ErrorListener { Toast.makeText(this,"Revise la conexión", Toast.LENGTH_LONG).show() })
        queue.add(stringRequest)

    }

    private fun consultarQR2() {




    }


}