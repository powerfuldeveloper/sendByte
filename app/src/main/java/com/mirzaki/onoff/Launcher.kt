package com.mirzaki.onoff

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.wch.ch34xuartdriver.CH34xUARTDriver
import kotlin.system.exitProcess


class Launcher : AppCompatActivity() {


    val TAG = "com.mirzaki.onoff"
    private val ACTION_USB_PERMISSION = "cn.wch.wchusbdriver.USB_PERMISSION"

    private lateinit var mService: ExampleService
    private var mBound: Boolean = false

    var selectedString = "Nothing"


    var isOpen = false
    private var handler: Handler? = null
    private var activity: Launcher? = null

    lateinit var on1: Button
    lateinit var off1: Button
    lateinit var on2: Button
    lateinit var off2: Button
    lateinit var on3: Button
    lateinit var off3: Button
    lateinit var on4: Button
    lateinit var off4: Button
    lateinit var on5: Button
    lateinit var off5: Button
    lateinit var on6: Button
    lateinit var off6: Button
    lateinit var on7: Button
    lateinit var off7: Button

    lateinit var writeBuffer: ByteArray
    lateinit var readBuffer: ByteArray
    var actualNumBytes = 0

    var baudRate = 9600
    var stopBit: Byte = 0
    var dataBit: Byte = 0
    var parity: Byte = 0
    var flowControl: Byte = 0


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

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        App.driver = CH34xUARTDriver(
            getSystemService(Context.USB_SERVICE) as UsbManager, this,
            ACTION_USB_PERMISSION
        )

        initUI()

        initSettings()

        if (!App.driver.UsbFeatureSupported())
        {
            val dialog: Dialog = AlertDialog.Builder(this@Launcher)
                .setTitle("Problem")
                .setMessage("Your phone does not support USB HOST !")
                .setPositiveButton(
                    "OK"
                ) { _, _ -> exitProcess(0) }.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }

        writeBuffer = ByteArray(512)
        readBuffer = ByteArray(512)
        isOpen = false
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        activity = this
        handler = Handler(Handler.Callback {
            when(it.obj as String) {
                on1_str -> {
                    on1.isEnabled = true
                    off1.isEnabled = false
                }
                off1_str -> {
                    on1.isEnabled = false
                    off1.isEnabled = true
                }
                on2_str -> {
                    on2.isEnabled = true
                    off2.isEnabled = false
                }
                off2_str -> {
                    on2.isEnabled = false
                    off2.isEnabled = true
                }
                on3_str -> {
                    on3.isEnabled = true
                    off3.isEnabled = false
                }
                off3_str -> {
                    on3.isEnabled = false
                    off3.isEnabled = true
                }
                on4_str -> {
                    on4.isEnabled = true
                    off4.isEnabled = false
                }
                off4_str -> {
                    on4.isEnabled = false
                    off4.isEnabled = true
                }
                on5_str -> {
                    on5.isEnabled = true
                    off5.isEnabled = false
                }
                off5_str -> {
                    on5.isEnabled = false
                    off5.isEnabled = true
                }
                on6_str -> {
                    on6.isEnabled = true
                    off6.isEnabled = false
                }
                off6_str -> {
                    on6.isEnabled = false
                    off6.isEnabled = true
                }
                on7_str -> {
                    on7.isEnabled = true
                    off7.isEnabled = false
                }
                off7_str -> {
                    on7.isEnabled = false
                    off7.isEnabled = true
                }
            }

            return@Callback  true
        })
    }

    private fun initSettings() {
        sharedPref = getSharedPreferences("SOME_KEYS", Context.MODE_PRIVATE)
        on1_str = sharedPref.getString("on1", "on1").toString()
        off1_str = sharedPref.getString("off1", "off1").toString()
        on2_str = sharedPref.getString("on2", "on2").toString()
        off2_str = sharedPref.getString("off2", "off2").toString()
        on3_str = sharedPref.getString("on3", "on3").toString()
        off3_str = sharedPref.getString("off3", "off3").toString()
        on4_str = sharedPref.getString("on4", "on4").toString()
        off4_str = sharedPref.getString("off4", "off4").toString()
        on5_str = sharedPref.getString("on5", "on5").toString()
        off5_str = sharedPref.getString("off5", "off5").toString()
        on6_str = sharedPref.getString("on6", "on6").toString()
        off6_str = sharedPref.getString("off6", "off6").toString()
        on7_str = sharedPref.getString("on7", "on7").toString()
        off7_str = sharedPref.getString("off7", "off7").toString()
    }

    private fun initUI() {
        on1 = findViewById(R.id.on_1)
        off1 = findViewById(R.id.off_1)
        on2 = findViewById(R.id.on_2)
        off2 = findViewById(R.id.off_2)
        on3 = findViewById(R.id.on_3)
        off3 = findViewById(R.id.off_3)
        on4 = findViewById(R.id.on_4)
        off4 = findViewById(R.id.off_4)
        on5 = findViewById(R.id.on_5)
        off5 = findViewById(R.id.off_5)
        on6 = findViewById(R.id.on_6)
        off6 = findViewById(R.id.off_6)
        on7 = findViewById(R.id.on_7)
        off7 = findViewById(R.id.off_7)
        disableButtons()
    }

    fun on1(view: View) {
        when (view.id) {
            R.id.on_1 -> {
            }
            R.id.on_2 -> {
            }
            R.id.on_3 -> {
            }
            R.id.on_4 -> {
            }
            R.id.on_5 -> {
            }
            R.id.on_6 -> {
            }
            R.id.on_7 -> {
            }
            R.id.off_1 -> {
            }
            R.id.off_2 -> {
            }
            R.id.off_3 -> {
            }
            R.id.off_4 -> {
            }
            R.id.off_5 -> {
            }
            R.id.off_6 -> {
            }
            R.id.off_7 -> {
            }
        }
    }

    fun off1(view: View) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Intent(this, Settings::class.java).also {
                    startActivity(it)
                }
            }
        }
        return true
    }

    fun sendDataToDevice(str:String){
        val to_send2 = toByteArray2(str)
        if (to_send2 != null) {
            val retval1: Int = App.driver.WriteData(
                to_send2,
                to_send2.size
            )

            if (retval1 < 0) Toast.makeText(
                this@Launcher, "Failed to send data!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


    fun enableButtons() {
        on1.isEnabled = true
        on2.isEnabled = true
        on3.isEnabled = true
        on4.isEnabled = true
        on5.isEnabled = true
        on6.isEnabled = true
        on7.isEnabled = true
    }

    fun disableButtons() {
        on1.isEnabled = false
        on2.isEnabled = false
        on3.isEnabled = false
        on4.isEnabled = false
        on5.isEnabled = false
        on6.isEnabled = false
        on7.isEnabled = false
        off1.isEnabled = false
        off2.isEnabled = false
        off3.isEnabled = false
        off4.isEnabled = false
        off5.isEnabled = false
        off6.isEnabled = false
        off7.isEnabled = false
    }

    private var retval = 0
    fun connectToDevice(view: View) {
        var openButton = view as Button
        if (!isOpen) {
            disableButtons()
            retval = App.driver.ResumeUsbList()
            if (retval == -1) {
                Toast.makeText(
                    this@Launcher, "Failed to open device!",
                    Toast.LENGTH_SHORT
                ).show()
                App.driver.CloseDevice()
            } else if (retval == 0) {
                if (!App.driver.UartInit()) { //对串口设备进行初始化操作
                    Toast.makeText(
                        this@Launcher, "Device initialization failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                    Toast.makeText(
                        this@Launcher, "Failed to turn on device",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                Toast.makeText(
                    this@Launcher, "Device was opened successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                isOpen = true
                openButton.text = "Disconnect"
                enableButtons()
                ReadThread().start()
            } else {
                val builder = AlertDialog.Builder(activity)
                builder.setIcon(R.mipmap.ic_launcher)
                builder.setTitle("Unauthorized limit")
                builder.setMessage("Are you sure to log out?")
                builder.setPositiveButton(
                    "Yes"
                ) { _, _ ->
                    exitProcess(0)
                }
                builder.setNegativeButton(
                    "No"
                ) { _, _ ->
                }
                builder.show()
                disableButtons()
            }
        }
        else {
            disableButtons()
            openButton.text = "Connect"
            isOpen = false
            try {
                Thread.sleep(200)
            } catch (e: InterruptedException) { // TODO Auto-generated catch block
                e.printStackTrace()
            }
            App.driver.CloseDevice()
            totalrecv = 0
        }
    }

    private fun toByteArray(arg: String?): ByteArray? {
        if (arg != null) {
            val newArray = CharArray(1000)
            val array = arg.toCharArray()
            var length = 0
            for (i in array.indices) {
                if (array[i] != ' ') {
                    newArray[length] = array[i]
                    length++
                }
            }

            val evenLength = if (length % 2 == 0) length else length + 1
            if (evenLength != 0) {
                val data = IntArray(evenLength)
                data[evenLength - 1] = 0
                for (i in 0 until length) {
                    when {
                        newArray[i] in '0'..'9' -> {
                            data[i] = newArray[i] - '0'
                        }
                        newArray[i] in 'a'..'f' -> {
                            data[i] = newArray[i] - 'a' + 10
                        }
                        newArray[i] in 'A'..'F' -> {
                            data[i] = newArray[i] - 'A' + 10
                        }
                    }
                }

                val byteArray = ByteArray(evenLength / 2)
                for (i in 0 until evenLength / 2) {
                    byteArray[i] = (data[i * 2] * 16 + data[i * 2 + 1]).toByte()
                }
                return byteArray
            }
        }
        return byteArrayOf()
    }

    private fun toByteArray2(arg: String?): ByteArray? {
        if (arg != null) {
            val newArray = CharArray(1000)
            val array = arg.toCharArray()
            var length = 0
            for (i in array.indices) {
                if (array[i] != ' ') {
                    newArray[length] = array[i]
                    length++
                }
            }
            newArray[length] = 0x0D.toChar()
            newArray[length + 1] = 0x0A.toChar()
            length += 2
            val byteArray = ByteArray(length)
            for (i in 0 until length) {
                byteArray[i] = newArray[i].toByte()
            }
            return byteArray
        }
        return byteArrayOf()
    }


    private fun toHexString(arg: ByteArray?, length: Int): String? {
        var result = String()
        if (arg != null) {
            for (i in 0 until length) {
                result = (result
                        + (if (Integer.toHexString(
                        if (arg[i] < 0) arg[i] + 256 else arg[i].toInt()
                    ).length == 1
                ) "0"
                        + Integer.toHexString(
                    if (arg[i] < 0) arg[i] + 256 else arg[i].toInt()
                ) else Integer.toHexString(
                    if (arg[i] < 0) arg[i] + 256 else arg[i].toInt()
                )) + " ")
            }
            return result
        }
        return ""
    }


    var totalrecv = 0
    inner class ReadThread : Thread() {
        override fun run() {
            val buffer = ByteArray(4096)
            while (true) {
                val msg = Message.obtain()
                if (!isOpen) {
                    break
                }
                val length: Int = App.driver.ReadData(buffer, buffer.size)
                if (length > 0) { //					String recv = toHexString(buffer, length);
//					String recv = new String(buffer, 0, length);
                    totalrecv += length
                    //                    String content = String.valueOf(totalrecv);
//                    String content = new String(buffer);
//                    String content = hexStringToString(toHexString(buffer,length));
                    var content: String? = toHexString(buffer, length * 2)
                    if (content == null)
                        content = ""
                    msg.obj = content + ""
                    handler?.sendMessage(msg)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (!App.driver.isConnected) {
            val retval: Int = App.driver.ResumeUsbPermission()
            /*if (retval == 0) {
            } else */if (retval == -2) {
                Toast.makeText(
                    this@Launcher, "Failed to obtain permission!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
