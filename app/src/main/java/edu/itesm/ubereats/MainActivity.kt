package edu.itesm.ubereats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    data class Compras(var total: Double)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicio()

    }

    fun inicio(){
        val comprasLista = mutableListOf<Compras>()
        var totFinal = 0.0

        diezPorciento.setOnClickListener {
            totFinal = precioTotal(1.1)
        }

        quincePorciento.setOnClickListener {
            totFinal = precioTotal(1.15)
        }

        veintePorciento.setOnClickListener {
            totFinal = precioTotal(1.2)
        }

        veinticincoPorciento.setOnClickListener {
            totFinal = precioTotal(1.25)
        }

        ceroPorciento.setOnClickListener {
            totFinal = precioTotal(0.0)
        }

        total.setOnClickListener {
            if(totFinal != 0.0){
                val totFinalClass = Compras(totFinal)
                comprasLista.add(totFinalClass)
                Log.i(  "edu.itesm.daec",  "Esta es comprasLista $comprasLista")
                sof.text = "$ 0.0"
                sf.text =  "$ 0.0"
                df.text =  "$ 0.0"
                subt.text.clear()
                total.text = " Total:                          $ 0.0"
            }else{
                Toast.makeText(this, "Inserta un subtotal válido", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun precioTotal(porcentaje: Double): Double{
        val subtotal = subt.text.toString().toDouble()
        val smallOrderFee = subtotal * 0.02
        val serviceFee = subtotal * 0.05
        val deliveryFee = subtotal * 0.1
        val tot = subtotal + smallOrderFee + serviceFee + deliveryFee
        sof.text = "$smallOrderFee"
        sf.text = "$serviceFee"
        df.text = "$deliveryFee"
        if(porcentaje == 0.0){
            total.text = " Total:                          $ $tot"
            return tot
        }else {
            val cerop = tot * porcentaje
            total.text = " Total:                          $ $cerop"
            return cerop
        }

    }
}