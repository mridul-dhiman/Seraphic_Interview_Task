package info.example.interviewtask.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import info.example.interviewtask.databinding.ActivitySelectGiftCardBinding
import info.example.interviewtask.models.GiftCard
import info.example.interviewtask.viewModels.SelectGiftCardViewModel
import info.example.interviewtask.views.recyclerView.GiftCardRecyclerViewAdapter

class SelectGiftCardActivity : AppCompatActivity(), GiftCardRecyclerViewAdapter.ClickListener {
    private lateinit var binding: ActivitySelectGiftCardBinding

    private val selectGiftCardViewModel: SelectGiftCardViewModel by viewModels()

    private lateinit var rvAdapter: GiftCardRecyclerViewAdapter
    private val gridLayoutManager: GridLayoutManager = GridLayoutManager(this, 2)

    private val giftCardList: ArrayList<GiftCard> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectGiftCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAdapter = GiftCardRecyclerViewAdapter(giftCardList, this)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (giftCardList[position].off.length < 8) {
                    1
                } else {
                    2
                }
            }

        }

        binding.apply {
            selectGiftCardActivityRV.apply {
                adapter = rvAdapter
                layoutManager = gridLayoutManager
            }

            selectGiftCardActivityLLNext.setOnClickListener {
                Log.d("SelectGiftCardActivity", "onCreate: next clicked")
            }
        }

        subscribeObserver()

        selectGiftCardViewModel.fetchGiftCards()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun subscribeObserver() {
        selectGiftCardViewModel.getGiftCardList().observe(this) {
            if (it.isNotEmpty()) {
                giftCardList.clear()
                giftCardList.addAll(it)
                rvAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClickListener(position: Int) {
        selectGiftCardViewModel.markSelected(position)
    }
}