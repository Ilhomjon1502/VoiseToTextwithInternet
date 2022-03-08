package uz.ilhomjon.voisetotext1

import android.R.attr
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.ilhomjon.voisetotext1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startDictation.setOnClickListener {

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            try {
                startActivityForResult(intent, 1)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(this, "Error ${a.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {


//If RESULT_OK is returned...//
                if (resultCode === RESULT_OK && null != attr.data) {

//...then retrieve the ArrayList//
                    val result: ArrayList<String> =
                        data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!

//Update our TextView//
                    binding.textOutput.setText(result[0])
                }
            }
        }
    }
}