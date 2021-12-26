package info.example.interviewtask.views.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.example.interviewtask.models.GiftCard
import info.example.interviewtask.R

class GiftCardRecyclerViewAdapter(
    private val cardList: ArrayList<GiftCard>,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val FullWidthCard = 1
        private const val HalfWidthCard = 2
    }

    interface ClickListener {
        fun onClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FullWidthCard) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_full_width_card, parent, false)
            FullWidthCardViewHolder(view, clickListener)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_half_width_card, parent, false)
            HalfWidthCardViewHolder(view, clickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (cardList[position].off.length > 8) {
            FullWidthCard
        } else {
            HalfWidthCard
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (cardList[position].off.length > 8) {
            val viewHolder = holder as FullWidthCardViewHolder
            viewHolder.onBind(cardList[position])
        } else {
            val viewHolder = holder as HalfWidthCardViewHolder
            viewHolder.onBind(cardList[position])
        }
    }

    override fun getItemCount(): Int = cardList.size
}