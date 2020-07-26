package com.mirzaki.onoff

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    var on1_str: String = ""
    var off1_str: String = ""
    var on2_str: String = ""
    var off2_str: String = ""
    var on3_str: String = ""
    var off3_str: String = ""
    var on4_str: String = ""
    var off4_str: String = ""
    var on5_str: String = ""
    var off5_str: String = ""
    var on6_str: String = ""
    var off6_str: String = ""
    var on7_str: String = ""
    var off7_str: String = ""

    lateinit var on1: EditText
    lateinit var off1: EditText
    lateinit var on2: EditText
    lateinit var off2: EditText
    lateinit var on3: EditText
    lateinit var off3: EditText
    lateinit var on4: EditText
    lateinit var off4: EditText
    lateinit var on5: EditText
    lateinit var off5: EditText
    lateinit var on6: EditText
    lateinit var off6: EditText
    lateinit var on7: EditText
    lateinit var off7: EditText

    lateinit var sharedPref:SharedPreferences

    fun strToEditable(str: String): Editable {
        return SpannableStringBuilder(str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPref = getSharedPreferences("SOME_KEYS", Context.MODE_PRIVATE)
        on1_str = sharedPref.getString("on1", "1:on").toString()
        off1_str = sharedPref.getString("off1", "1:off").toString()
        on2_str = sharedPref.getString("on2", "2:on").toString()
        off2_str = sharedPref.getString("off2", "2:off").toString()
        on3_str = sharedPref.getString("on3", "3:on").toString()
        off3_str = sharedPref.getString("off3", "3:off").toString()
        on4_str = sharedPref.getString("on4", "4:on").toString()
        off4_str = sharedPref.getString("off4", "4:off").toString()
        on5_str = sharedPref.getString("on5", "4:on").toString()
        off5_str = sharedPref.getString("off5", "5:off").toString()
        on6_str = sharedPref.getString("on6", "6:on").toString()
        off6_str = sharedPref.getString("off6", "6:off").toString()
        on7_str = sharedPref.getString("on7", "7:on").toString()
        off7_str = sharedPref.getString("off7", "7:off").toString()

        on1 = findViewById(R.id.on_text_1)
        off1 = findViewById(R.id.off_text_1)
        on2 = findViewById(R.id.on_text_2)
        off2 = findViewById(R.id.off_text_2)
        on3 = findViewById(R.id.on_text_3)
        off3 = findViewById(R.id.off_text_3)
        on4 = findViewById(R.id.on_text_4)
        off4 = findViewById(R.id.off_text_4)
        on5 = findViewById(R.id.on_text_5)
        off5 = findViewById(R.id.off_text_5)
        on6 = findViewById(R.id.on_text_6)
        off6 = findViewById(R.id.off_text_6)
        on7 = findViewById(R.id.on_text_7)
        off7 = findViewById(R.id.off_text_7)

        on1.text = strToEditable(on1_str)
        off1.text = strToEditable(off1_str)
        on2.text = strToEditable(on2_str)
        off2.text = strToEditable(off2_str)
        on3.text = strToEditable(on3_str)
        off3.text = strToEditable(off3_str)
        on4.text = strToEditable(on4_str)
        off4.text = strToEditable(off4_str)
        on5.text = strToEditable(on5_str)
        off5.text = strToEditable(off5_str)
        on6.text = strToEditable(on6_str)
        off6.text = strToEditable(off6_str)
        on7.text = strToEditable(on7_str)
        off7.text = strToEditable(off7_str)
    }

    fun applySettings(view: View) {
        with(sharedPref.edit()) {
            putString("on1", on1.text.toString())
            putString("off1", off1.text.toString())
            putString("on2", on2.text.toString())
            putString("off2", off2.text.toString())
            putString("on3", on3.text.toString())
            putString("off3", off3.text.toString())
            putString("on4", on4.text.toString())
            putString("off4", off4.text.toString())
            putString("on5", on5.text.toString())
            putString("off5", off5.text.toString())
            putString("on6", on6.text.toString())
            putString("off6", off6.text.toString())
            putString("on7", on7.text.toString())
            putString("off7", off7.text.toString())
            commit()
        }
        finish()
    }

}
