package com.example.nuevo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.calculadora_imc.R
import com.google.android.material.slider.Slider
import kotlin.math.pow

class
MainActivity : AppCompatActivity() {

    // Weight
    lateinit var removeWeightButton: Button
    lateinit var addWeightButton: Button
    lateinit var weightTextView: TextView

    // Height
    lateinit var heightSlider: Slider
    lateinit var heightTextView: TextView

    // Result
    lateinit var calculateButton: Button
    lateinit var resultTextView: TextView
    lateinit var fraseTextView: TextView

    var weight = 74.0f
    var height = 170.0f

    // Sexo
    lateinit var femaleButton: Button
    lateinit var maleButton: Button

    // gif
    lateinit var gifImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        removeWeightButton = findViewById(R.id.removeWeightButton)
        addWeightButton = findViewById(R.id.addWeightButton)
        weightTextView = findViewById(R.id.weightTextView)
        heightSlider = findViewById(R.id.heightSlider)
        heightTextView = findViewById(R.id.heightTextView)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
        fraseTextView = findViewById(R.id.fraseTextView)
        femaleButton = findViewById(R.id.buttonFemale)
        maleButton = findViewById(R.id.buttonMale)
        gifImageView = findViewById(R.id.gifImageView)

        removeWeightButton.setOnClickListener {
            weight--
            weightTextView.text = "${weight.toInt()} kg"
        }
        addWeightButton.setOnClickListener {
            weight++
            weightTextView.text = "${weight.toInt()} kg"
        }

        heightSlider.addOnChangeListener { slider, value, fromUser ->
            height = value
            heightTextView.text = "${value.toInt()} cm"
        }

        calculateButton.setOnClickListener {
            val result = weight / (height / 100).pow(2)
            resultTextView.text = String.format("%.2f", result)
            maleButton.isEnabled = true
            femaleButton.isEnabled = true
        }

        // Botón izquierdo (buscar en conjunto de textos 1 - Male)
        maleButton.setOnClickListener {
            val frase = resultTextView.text.toString().toDoubleOrNull() // Obtener el valor de resultTextView
            if (frase != null) {
                // Llamar a la función para mostrar el texto y el GIF para "Male"
                searchInText1(frase)
                cargarGifMale(frase, gifImageView) // Cargar el GIF para "Male"
            } else {
                Toast.makeText(this, "Por favor, ingrese un valor válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Botón derecho (buscar en conjunto de textos 2 - Female)
        femaleButton.setOnClickListener {
            val frase = resultTextView.text.toString().toDoubleOrNull() // Obtener el valor de resultTextView
            if (frase != null) {
                // Llamar a la función para mostrar el texto y el GIF para "Female"
                searchInText2(frase)
                cargarGifFemale(frase, gifImageView) // Cargar el GIF para "Female"
            } else {
                Toast.makeText(this, "Por favor, ingrese un valor válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    // Función para buscar en el conjunto de textos Male basado en el valor de la frase
    private fun searchInText1(frase: Double) {
        val resultText = when (frase) {
            in 0.1..18.5 -> "Te va a llevar el Diablo RIP!!!!"
            in 18.5..24.9 -> "Sigue en el Ruedo!!!"
            in 25.0..29.9 -> "Deja la Play!!!"
            in 30.0..34.9 -> "Es tiempo de Ir al GYM Vaca"
            in 35.0..39.0 -> "Marzopa Levante y Anda"
            else -> "Bola de Matenca solo Puedes Rodar!!!"
        }
        // Actualizar el texto correspondiente
        fraseTextView.text = resultText
    }

    // Función para buscar en el conjunto de textos Female basado en el valor de la frase
    private fun searchInText2(frase: Double) {
        val resultText = when (frase) {
            in 0.1..18.5 -> "Te va a llevar el Diablo RIP!!!!"
            in 18.5..24.9 -> "La Diva!!!"
            in 25.0..29.9 -> "Ponte a Saltar la Cuerda!!!"
            in 30.0..34.9 -> "Es Tiempo de ir al GYM Vaca"
            in 35.0..39.0 -> "Marzopa Levante y Anda"
            else -> "Bola de Matenca solo Puedes Rodar!!!"
        }
        // Actualizar el texto correspondiente
        fraseTextView.text = resultText
    }

    // Función para cargar el GIF según el valor de resultText para el caso Male
    private fun cargarGifMale(frase: Double, gifImageView: ImageView) {
        when (frase) {
            in 0.1..18.5 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_manskinny) // GIF para rango 0.1-18.5
                    .into(gifImageView)
            }
            in 18.5..24.9 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_manruedo) // GIF para rango 18.5-24.9
                    .into(gifImageView)
            }
            in 25.0..29.9 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_manfat) // GIF para rango 25.0-29.9
                    .into(gifImageView)
            }
            in 30.0..34.9 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_manfat) // GIF para rango 30.0-34.9
                    .into(gifImageView)
            }
            in 35.0..39.0 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_manfat) // GIF para rango 35.0-39.0
                    .into(gifImageView)
            }
            else -> {
                gifImageView.setImageDrawable(null) // Eliminar el GIF si no está en rango
            }
        }
    }

    // Función para cargar el GIF según el valor de resultText para el caso Female
    private fun cargarGifFemale(frase: Double, gifImageView: ImageView) {
        when (frase) {
            in 0.1..18.5 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_grilskeleton) // GIF para rango 0.1-18.5
                    .into(gifImageView)
            }
            in 18.5..24.9 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_diva) // GIF para rango 18.5-24.9
                    .into(gifImageView)
            }
            in 25.0..29.9 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_diva) // GIF para rango 25.0-29.9
                    .into(gifImageView)
            }
            in 30.0..34.9 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_fatgirtoons) // GIF para rango 30.0-34.9
                    .into(gifImageView)
            }
            in 35.0..39.0 -> {
                Glide.with(this)
                    .asGif()
                    .load(R.drawable.gif_girlfat) // GIF para rango 35.0-39.0
                    .into(gifImageView)
            }
            else -> {
                gifImageView.setImageDrawable(null) // Eliminar el GIF si no está en rango
            }
        }
    }
}
