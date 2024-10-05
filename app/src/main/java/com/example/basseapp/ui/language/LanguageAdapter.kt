package com.example.basseapp.ui.language

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basseapp.R
import com.example.basseapp.base.BaseAdapter
import com.example.basseapp.databinding.ItemLanguageBinding
import com.example.basseapp.model.LanguageModel

class LanguageAdapter(
    val context: Context,
    val onClick: (lang: LanguageModel) -> Unit
) : BaseAdapter<ItemLanguageBinding, LanguageModel>() {

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemLanguageBinding {
        return ItemLanguageBinding.inflate(inflater, parent, false)
    }

    override fun createVH(binding: ItemLanguageBinding): RecyclerView.ViewHolder =
        LanguageVH(binding)


    inner class LanguageVH(binding: ItemLanguageBinding) : BaseVH<LanguageModel>(binding) {
        override fun onItemClickListener(data: LanguageModel) {
            super.onItemClickListener(data)
            onClick.invoke(data)
        }

        override fun bind(data: LanguageModel) {
            super.bind(data)
            binding.txtName.text = data.name

            when (data.code) {
                "en" -> binding.icLang.setImageResource(R.drawable.flag_en)
                "de" -> binding.icLang.setImageResource(R.drawable.flag_ger)
                "es" -> binding.icLang.setImageResource(R.drawable.flag_span)
                "fr" -> binding.icLang.setImageResource(R.drawable.flag_fra)
                "hi" -> binding.icLang.setImageResource(R.drawable.flag_hindi)
                "in" -> binding.icLang.setImageResource(R.drawable.flag_indonesia)
                "pt" -> binding.icLang.setImageResource(R.drawable.flag_port)
                "vi" -> binding.icLang.setImageResource(R.drawable.ic_vi_flag)
                "ja" -> binding.icLang.setImageResource(R.drawable.ic_japan_flag)
            }
            if (data.active)
                binding.layoutItem.setBackgroundResource(R.drawable.border_item_language_select)
            else
                binding.layoutItem.setBackgroundResource(R.drawable.border_item_language)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setCheck(code: String) {
        for (item in listData) {
            item.active = item.code == code
        }
        notifyDataSetChanged()
    }
}