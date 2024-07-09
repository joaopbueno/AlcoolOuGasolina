package com.example.gasolinaoualcool

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool : TextInputLayout
    private lateinit var textEditAlcool : TextInputEditText

    private lateinit var textInputGasolina : TextInputLayout
    private lateinit var textEditGasolina : TextInputEditText

    private lateinit var btnCalcular : Button
    private lateinit var textResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarComponentesDeInterface()
        btnCalcular.setOnClickListener {
            calcularMelhorPreço()
        }
    }

    private fun calcularMelhorPreço() {
        val precoAlcool = textEditAlcool.text.toString()
        val precoGasolina = textEditGasolina.text.toString()

        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)
        if(resultadoValidacao){
            val total = precoAlcool.toDouble() / precoGasolina.toDouble()
            if(total >= 0.7){
                textResultado.text = "Melhor utilizar Gasolina!"
            }
            else{
                textResultado.text = "Melhor utilizar Alcool!"
            }
        }
    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {
        textInputAlcool.error = null
        textInputGasolina.error = null

        if(pAlcool.isEmpty()){
            textInputAlcool.error =  "Digite o valor corretamente!"
            return false
        }
        else if(pGasolina.isEmpty()){
            textInputGasolina.error =  "Digite o valor corretamente!"
            return false
        }

        return true
    }

    private fun inicializarComponentesDeInterface() {
        textInputAlcool = findViewById(R.id.text_input_alcool)
        textEditAlcool = findViewById(R.id.edit_alcool)

        textInputGasolina = findViewById(R.id.text_input_gasolina)
        textEditGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)
        textResultado = findViewById(R.id.txt_resultado)
    }
}