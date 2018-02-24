package ru.thstdio.geek.weather

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TEXT_COLOR = "ru.thstdio.geek.weather.color"
        private const val PREFS_FILENAME = "ru.thstdio.geek.weather.prefs"
        private const val LAST_CITY = "LAST_CITY"
        private const val REQUEST_CHANGE_FON = 1
    }

    var prefs: SharedPreferences? = null


    var cityWeather: Array<out String>? = null

    override fun onClick(p0: View?) {
        when (p0) {
            btnUpdate -> checkSpiner();
            btnSendAll -> clickBtnSendAll();
            btnChangeFon -> clickBtnChangeFon();
            btnStartWithPM -> clicWithPackageManager()
            btnStartWithoutPM -> clicWithOutPackageManager()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnUpdate.setOnClickListener(this);
        btnSendAll.setOnClickListener(this);
        btnChangeFon.setOnClickListener(this);
        btnStartWithPM.setOnClickListener(this);
        btnStartWithoutPM.setOnClickListener(this);
        cityWeather = this.resources.getStringArray(R.array.cityWeather);
        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        val lastCity = prefs!!.getInt(LAST_CITY, 0)
        spinnerCity.setSelection(lastCity)
    }

    fun toastMe(str: String) {
        val myToast = Toast.makeText(this, str, Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun checkSpiner() {
        var position = spinnerCity.selectedItemPosition
        //  toastMe(cityWeather!![position]);
        txtWeather.setText(cityWeather!![position])
        val editor = prefs!!.edit()
        editor.putInt(LAST_CITY, position)
        editor.apply()
    }

    private fun clickBtnSendAll() {
        intent = Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, txtWeather.text.toString())
        intent.setType("text/plain");
        startActivity(intent)
    }

    private fun clickBtnChangeFon() {
        intent = Intent(this, ChangeFonActivity::class.java)
        startActivityForResult(intent, REQUEST_CHANGE_FON)
    }

    private fun clicWithPackageManager() {
        val i = Intent("GoToMars")
        if (i.resolveActivity(packageManager) != null) startActivity(i)
        else toastMe("Error Activity No Exist")
    }

    private fun clicWithOutPackageManager() {
        val i = Intent("GoToMars")
        startActivity(i)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CHANGE_FON ->
                if (resultCode == Activity.RESULT_OK)
                    when (data!!.getIntExtra(TEXT_COLOR, 0)) {
                        0 -> txtWeather.setTextColor(Color.RED)
                        1 -> txtWeather.setTextColor(Color.BLUE)
                        2 -> txtWeather.setTextColor(Color.GREEN)
                        3 -> txtWeather.setTextColor(Color.YELLOW)
                    }
        }
    }

}
