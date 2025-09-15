package br.edu.ifsp.scl.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.fastcalculation.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var receivedPoints: Float = 0.0f
    private lateinit var fragmentResultBinding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            receivedPoints = it.getFloat(ARG_POINTS_FLOAT, 0.0f)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentResultBinding = FragmentResultBinding.inflate(inflater,container, false)

        fragmentResultBinding.restartValueResult.text = "%.1f".format(receivedPoints)

        fragmentResultBinding.restarBtnResult.setOnClickListener {
            (context as OnPlayGame).onPlayGame()
        }


        return fragmentResultBinding.root
    }

    companion object {
        private const val ARG_POINTS_FLOAT = "arg_points_float"
        @JvmStatic
        fun newInstance(points: Float) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putFloat(ARG_POINTS_FLOAT, points)
                }
            }
    }

}