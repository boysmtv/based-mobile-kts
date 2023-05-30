package com.based.kotlin.core.ui.dialog.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.based.kotlin.core.entity.util.Lov
import com.based.kotlin.core.ui.R
import com.based.kotlin.core.ui.databinding.ItemRvCancellationBinding
import com.based.kotlin.core.ui.extensions.hide
import com.based.kotlin.core.ui.extensions.show

class DialogCancellationAdapter(
    private val onItemClickListener: (Lov) -> Unit
) : RecyclerView.Adapter<DialogCancellationAdapter.DialogCancellationViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION
    private var dataLov = mutableListOf<Lov>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DialogCancellationViewHolder = DialogCancellationViewHolder(
        ItemRvCancellationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(
        holderDialogCancellation: DialogCancellationViewHolder,
        position: Int
    ) =
        holderDialogCancellation.bindView(dataLov[position])

    override fun getItemCount(): Int = dataLov.size

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<Lov>) {
        dataLov.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class DialogCancellationViewHolder(private val binding: ItemRvCancellationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(data: Lov) = with(binding) {
            tvContentDialogCancelation.text = data.display

            if (adapterPosition == selectedPosition) {
                ivChecklistItemDialogCancellation.show()
                tvContentDialogCancelation.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.colorSoftBlueTextEnable
                    )
                )

            } else {
                ivChecklistItemDialogCancellation.hide()
                tvContentDialogCancelation.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.colorBlackUnselectedText
                    )
                )
            }

            itemView.setOnClickListener {
                onItemClickListener(data)
                selectedPosition = adapterPosition
                notifyDataSetChanged()
            }
        }

    }

}