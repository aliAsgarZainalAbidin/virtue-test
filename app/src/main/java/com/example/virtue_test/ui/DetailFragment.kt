package com.example.virtue_test.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.virtue_test.R
import com.example.virtue_test.databinding.FragmentDetailBinding
import com.example.virtue_test.db.models.Quote
import com.example.virtue_test.utils.mainNavController
import com.example.virtue_test.utils.showToast
import com.example.virtue_test.viewmodels.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var _binding: FragmentDetailBinding
    private val binding get() = _binding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quote: Quote? = arguments?.getParcelable("quote")
        with(binding) {
            mtvRvitemQuote.text = quote?.quote
            mtvRvitemPeople.text = "~ ${quote?.author}"
            val data = "${quote?.quote} ~ ${quote?.author}"

            ivDetailBack.setOnClickListener {
                mainNavController().popBackStack()
            }

            ivDetailSave.setOnClickListener {
                if (quote != null) {
                    quoteViewModel.insertQuote(quote)
                }
            }

            ivDetailCopy.setOnClickListener {
                val clipManager =
                    requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("text", data)
                clipManager.setPrimaryClip(clip)

                requireContext().showToast("Berhasil Copy Quote")
            }

            ivDetailShare.setOnClickListener {
                val intent = Intent(ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT,data)
                startActivity(Intent.createChooser(intent,"Share"))
            }
        }
    }
}