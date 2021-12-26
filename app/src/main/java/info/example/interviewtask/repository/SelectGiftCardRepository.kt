package info.example.interviewtask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.example.interviewtask.models.GiftCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SelectGiftCardRepository {
    private val giftCardList: MutableLiveData<List<GiftCard>> =
        MutableLiveData<List<GiftCard>>()

    private val normalGiftCardList: ArrayList<GiftCard> = ArrayList()

    fun generateGiftCardList() {

        CoroutineScope(Dispatchers.IO).launch {
            normalGiftCardList.clear()

            normalGiftCardList.add(GiftCard("60", "119.00", "15% OFF"))
            normalGiftCardList.add(GiftCard("90", "157.25", "15% OFF"))
            normalGiftCardList.add(GiftCard("2X60", "238.00", "15% OFF"))
            normalGiftCardList.add(GiftCard("2X90", "314.50", "15% OFF"))
            normalGiftCardList.add(GiftCard("60", "1068.00 (PAID IN FULL)", "SAVE OVER 40% FOR"))
            normalGiftCardList.add(GiftCard("90", "1428.00 (PAID IN FULL)", "SAVE OVER 40% FOR"))

            giftCardList.postValue(normalGiftCardList)
        }
    }

    fun markSelected(position: Int) {
        normalGiftCardList.forEach {
            if (it.isSelected) {
                it.isSelected = false
            }
        }

        normalGiftCardList[position].isSelected = true
        giftCardList.value = normalGiftCardList
    }

    fun getGiftCardList(): LiveData<List<GiftCard>> = giftCardList
}