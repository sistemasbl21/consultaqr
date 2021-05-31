package com.example.basedatos2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CAvanzado : AppCompatActivity() {

    var BIngresarp: Button?=null
    var BActualizar: Button?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cavanzado)

        BIngresarp=findViewById(R.id.bingresarp)
        BActualizar=findViewById(R.id.bactualizar)




    }

    override fun onResume() {
        super.onResume()

        BIngresarp!!.setOnClickListener{
            //me envia a otra atividad
            val i: Intent = Intent(this,IngresarP::class.java)
            startActivity(i)

        }

        BActualizar!!.setOnClickListener{

            val j: Intent = Intent(this,ActualizaryEliminar::class.java)
            startActivity(j)

        }

    }



}
