package com.example.virtue_test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.virtue_test.R
import com.example.virtue_test.databinding.RvItemQouteBinding
import com.example.virtue_test.db.models.Quote

class QuoteAdapter(
    private val arrayList: ArrayList<Quote>,
    private val navController: NavController
) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    class QuoteViewHolder(
        private val binding: RvItemQouteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote, navController: NavController) {
            with(binding) {
                mtvRvitemQuote.text = quote.quote
                mtvRvitemPeople.text = "~ ${quote.author}"
                cv.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putParcelable("quote", quote)
                    navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuoteAdapter.QuoteViewHolder {
        val view = RvItemQouteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteAdapter.QuoteViewHolder, position: Int) {
        holder.bind(arrayList[position], navController)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}