package gq.jetpack.lingering.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author <a href="mailto:ogtc890215@gmail.com">guqi</a>
 */
class DataBoundViewHolder<out T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)