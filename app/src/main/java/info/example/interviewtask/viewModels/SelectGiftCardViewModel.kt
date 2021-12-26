package info.example.interviewtask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import info.example.interviewtask.models.GiftCard
import info.example.interviewtask.repository.SelectGiftCardRepository

class SelectGiftCardViewModel(private val repository: SelectGiftCardRepository = SelectGiftCardRepository) :
    ViewModel() {

    fun fetchGiftCards() {
        repository.generateGiftCardList()
    }

    fun markSelected(position: Int) {
        repository.markSelected(position)
    }

    fun getGiftCardList(): LiveData<List<GiftCard>> = repository.getGiftCardList()
}