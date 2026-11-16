package com.campany.calcchai

import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editBill = findViewById<EditText>(R.id.editBill)
        val seekBarPercent = findViewById<SeekBar>(R.id.seekBarPercent)
        val textPercent = findViewById<TextView>(R.id.textPercent)
        val textTip = findViewById<TextView>(R.id.textTip)
        val textTotal = findViewById<TextView>(R.id.textTotal)

        seekBarPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textPercent.text = "Чаевые: $progress%"

                val billText = editBill.text.toString()

                if (billText.isEmpty()) {
                    textTip.text = "Чаевые: 0"
                    textTotal.text = "Итого: 0"
                } else {
                    val bill = billText.toDouble()
                    val tip = bill * progress / 100
                    val total = bill + tip

                    textTip.text = "Чаевые: ${tip.toInt()}"
                    textTotal.text = "Итого: ${total.toInt()}"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}