package com.yusufaydin.catchthekennykotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yusufaydin.catchthekennykotlin.databinding.ActivityGameScreenBinding
import kotlin.random.Random

class GameScreen : AppCompatActivity() {

    private lateinit var binding: ActivityGameScreenBinding
    var number=15
    var runnable=Runnable{}
    var handler= Handler(Looper.getMainLooper())
    var score=0
    var imageArray=ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        //Image array
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)


        hideImages()
        //countdawn tımer
        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {//her sanıyede bır ne olsun
                binding.timeView.text="Time ${p0/1000}"

            }

            override fun onFinish() {
                binding.timeView.text="Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                //alert
                val alert= AlertDialog.Builder(this@GameScreen)
                alert.setTitle("Game over")
                alert.setMessage("Restart the Game?")

                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                    //restart yapıcaz burada
                    //buradakı mantık kendı maıne ıntent attık yanı en bastan baslatmak ıcın
                    //sonra fınısle kapattık sonra en bastan baslattık
                    val intentFromMAin=intent
                    finish()
                    startActivity(intent)
                    //en bastan baslatıcaz ve skor sıfılancak

                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@GameScreen,"Game Over",Toast.LENGTH_LONG).show()
                })
                alert.show()


            }

        }.start()


    }

    fun hideImages(){
        //View.gone ve View .Invisible arasında kı fark gone dırek cıkartıyor layouttan
        runnable= object : Runnable {
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE//butun fotoları gorunmez hale getırırız
                }
                val  random = Random
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE//bir dizinin ıcınden random sayıolusutur ve o foto  gozukur olucak
                handler.postDelayed(runnable,500)//kac sanıyede bır gozukecegi



            }

        }
        handler.post(runnable)//calıstırcaz





    }



    fun increaseScore(view: View){

        score +=1
        binding.scoreView.text="Score:${score}"

    }
}