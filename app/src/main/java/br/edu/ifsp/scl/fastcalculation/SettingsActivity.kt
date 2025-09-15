package br.edu.ifsp.scl.fastcalculation

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.fastcalculation.Extras.EXTRA_SETTINGS
import br.edu.ifsp.scl.fastcalculation.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private val activitySettingsBinding: ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(activitySettingsBinding.root)

        setSupportActionBar(activitySettingsBinding.gameTbIn.gameTab)
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.subtitle = "Settings"

        activitySettingsBinding.apply {
            lastGoBt.setOnClickListener {
                val settings = Settings(
                    payerNameEditText.text.toString(),
                    (roundesSP.selectedView as TextView).text.toString().toInt(),
                    roundIntervalRg.run {
                        findViewById<RadioButton>(checkedRadioButtonId).text.toString().toLong() * 1000L
                    }
                )

                val gameActivityIntent = Intent(this@SettingsActivity, GameActivity::class.java)
                gameActivityIntent.putExtra(EXTRA_SETTINGS,settings)
                startActivity(gameActivityIntent)
                finish()
            }
        }

    }
}