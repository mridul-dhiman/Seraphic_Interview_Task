package info.example.interviewtask.views.recyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import info.example.interviewtask.R
import info.example.interviewtask.models.GiftCard

class FullWidthCardViewHolder(
    itemView: View,
    private val click: GiftCardRecyclerViewAdapter.ClickListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val minText = itemView.findViewById<TextView>(R.id.fullWidthTVMinText)
    private val priceText = itemView.findViewById<TextView>(R.id.fullWidthTVPrice)
    private val offText = itemView.findViewById<TextView>(R.id.fullWidthTVOff)

    fun onBind(card: GiftCard) {
        minText.text = card.min
        priceText.text = card.amount
        offText.text = card.off

        itemView.setOnClickListener(this)

        val mcv: MaterialCardView = itemView as MaterialCardView

        mcv.apply {
            if (card.isSelected) {
                strokeColor = resources.getColor(R.color.custom_primary_color, null)
                strokeWidth = 4
            } else {
                strokeColor = resources.getColor(android.R.color.transparent, null)
            }
        }
    }

    override fun onClick(v: View?) {
        click.onClickListener(adapterPosition)
    }
}