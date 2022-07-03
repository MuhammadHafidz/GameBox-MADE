package id.haff.gamebox

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.lifecycleScope
import id.haff.gamebox.databinding.ActivitySplashScreenBinding
import id.haff.gamebox.home.MainActivity
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
@FlowPreview
@ObsoleteCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            delay(3000)
            binding.apply {
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                this@SplashScreenActivity.startActivity(intent)
                this@SplashScreenActivity.finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}