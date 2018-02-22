package ru.thstdio.geek.weather

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.thstdio.geek.weather.R.array.cityWeather


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var prefs: SharedPreferences? = null
    val PREFS_FILENAME = "ru.thstdio.geek.weather.prefs"
    val LAST_CITY ="LAST_CITY"
    var cityWeather: Array<out String>? = null

    override fun onClick(p0: View?) {
        if (p0 == btnUpdate)checkSpiner();
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnUpdate.setOnClickListener(this);
        cityWeather = this.resources.getStringArray(R.array.cityWeather);
        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        val lastCity = prefs!!.getInt(LAST_CITY, 0)
        spinnerCity.setSelection(lastCity)
    }

    fun toastMe(str: String) {
        val myToast = Toast.makeText(this, str, Toast.LENGTH_SHORT)
        myToast.show()
    }
    fun checkSpiner(){
        var position = spinnerCity.selectedItemPosition
      //  toastMe(cityWeather!![position]);
        txtWeather.setText(cityWeather!![position])
        val editor = prefs!!.edit()
        editor.putInt(LAST_CITY, position)
        editor.apply()
    }
}
