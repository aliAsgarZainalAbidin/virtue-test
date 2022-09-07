package com.example.virtue_test.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.virtue_test.db.Repository
import com.example.virtue_test.db.models.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private lateinit var mutableLiveDataQoute: MutableLiveData<Response<ArrayList<Quote>>>
    private lateinit var mutableLiveDataQuoteLocal: MutableLiveData<List<Quote>>

    fun getAllQoute(): LiveData<Response<ArrayList<Quote>>> {
        mutableLiveDataQoute = MutableLiveData()
        GlobalScope.launch {
            val response = repository.getAllQuotes()
            mutableLiveDataQoute.postValue(response)
        }
        return mutableLiveDataQoute
    }

    fun getAllLocalQuote(): LiveData<List<Quote>> {
        mutableLiveDataQuoteLocal = MutableLiveData()
        GlobalScope.launch {
            mutableLiveDataQuoteLocal.postValue(repository.getAllLocalQoutes())
        }
        return mutableLiveDataQuoteLocal
    }

    fun insertQuote(quote: Quote){
        GlobalScope.launch {
            repository.insertQuote(quote)
        }
    }

    fun deleteQuote(quote: Quote){
        GlobalScope.launch {
            repository.deleteQuote(quote)
        }
    }
}