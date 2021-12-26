package info.example.interviewtask.models

data class GiftCard(
    val min: String,
    val amount: String,
    val off: String,
    var isSelected: Boolean = false
)
