package ru.thstdio.geek.weather

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_change_fon.*
import kotlinx.android.synthetic.main.content_change_fon.*

var intColor: Int = 0

class ChangeFonActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when (p0!!.checkedRadioButtonId) {
            radioButtonRed.id -> intColor = 0
            radioButtonBlue.id -> intColor = 1
            radioButtonGreen.id -> intColor = 2
            radioButtonOrange.id -> intColor = 3
        }
    }

    private fun sendResult() {
        intent= Intent()
        intent.putExtra(MainActivity.TEXT_COLOR,intColor)
        setResult(RESULT_OK, intent);
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_fon)
        setSupportActionBar(toolbar)
        radio.setOnCheckedChangeListener(this)
        btnSendResult.setOnClickListener(View.OnClickListener { sendResult() })
    }

    fun toastMe(str: String) {
        val myToast = Toast.makeText(this, str, Toast.LENGTH_SHORT)
        myToast.show()
    }
}
