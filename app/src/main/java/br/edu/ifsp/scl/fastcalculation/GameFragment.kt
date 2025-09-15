package br.edu.ifsp.scl.fastcalculation

import android.media.audiofx.Equalizer
import android.os.Bundle
import android.os.Looper
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import br.edu.ifsp.scl.fastcalculation.Extras.EXTRA_SETTINGS
import br.edu.ifsp.scl.fastcalculation.databinding.FragmentGameBinding
import br.edu.scl.ifsp.sdm.fastcalculation.CalculationGame
import android.os.Handler
class GameFragment : Fragment() {
    private lateinit var  fragmentGameBinding: FragmentGameBinding
    private lateinit var  settings: Settings
    private lateinit var calculationGame: CalculationGame
    private var currenteRound : CalculationGame.Round? = null
    private var startRoundTime = 0L
    private var totalGameTime = 0L
    private var hits = 0

    private lateinit var setting: Settings

    private val roundDeadLineHandler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            totalGameTime += settings.roundInterval
            play()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            settings = it.getParcelable(EXTRA_SETTINGS) ?: Settings()
        }
        calculationGame = CalculationGame(settings.rounds)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentGameBinding = FragmentGameBinding.inflate(inflater, container, false)

        val onClickListener = View.OnClickListener {
            val value = (it as Button).text.toString().toInt()
            if (value == currenteRound?.answer) {
                totalGameTime += System.currentTimeMillis() - startRoundTime
                hits++
            } else {
                totalGameTime += settings.roundInterval
                hits--
            }
            roundDeadLineHandler.removeMessages(MSG_ROUND_DEADLINE)
            play()
        }

        fragmentGameBinding.apply {
            altertiveOneBt.setOnClickListener(onClickListener)
            altertiveTwoBt.setOnClickListener(onClickListener)
            altertiveThreeBt.setOnClickListener(onClickListener)
        }

        play()

        return fragmentGameBinding.root
    }

    companion object {

        private const val MSG_ROUND_DEADLINE = 0
        @JvmStatic
        fun newInstance(settings: Settings) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_SETTINGS, settings)
                }
            }
    }

    private fun play(){
        currenteRound = calculationGame.nextRound()
        if(currenteRound != null){
            fragmentGameBinding.apply {
                "Round : ${currenteRound!!.round}/${settings.rounds}".also {
                    roundTV.text = it
                }
                questionTV.text = currenteRound!!.question
                altertiveOneBt.text = currenteRound!!.alt1.toString()
                altertiveTwoBt.text = currenteRound!!.alt2.toString()
                altertiveThreeBt.text = currenteRound!!.alt3.toString()
            }
                startRoundTime = System.currentTimeMillis()
                roundDeadLineHandler.sendMessageDelayed(
                    roundDeadLineHandler.obtainMessage(MSG_ROUND_DEADLINE),
                    settings.roundInterval
                )
        } else {
            with(fragmentGameBinding){

                val points = hits * 10f / (totalGameTime/ 1000)

                "%.1f".format(points).also {
                    questionTV.text = it

                }

                val resultFragment = ResultFragment.newInstance(points)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.gameFl, resultFragment)
                    .commit()

            }
        }
    }
}