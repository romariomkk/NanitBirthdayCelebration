package com.romariomkk.nanitbirth.util.binding

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.romariomkk.nanitbirth.R
import com.romariomkk.nanitbirth.config.GlideApp
import com.romariomkk.nanitbirth.domain.pojo.ChildLife
import com.romariomkk.nanitbirth.ui.birthday.BirthdayUiState
import de.hdodenhof.circleimageview.CircleImageView
import timber.log.Timber

object BirthdayBinding {

    @JvmStatic
    @BindingAdapter("android:src")
    fun ImageView.bindUiMode(birthdayUiState: BirthdayUiState) {
        setImageResource(
            when (birthdayUiState) {
                is BirthdayUiState.Fox -> R.drawable.android_fox_popup
                is BirthdayUiState.Pelican -> R.drawable.android_pelican_popup
                is BirthdayUiState.Elephant -> R.drawable.android_elephant_popup
            }
        )
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun ImageButton.bindUiMode(birthdayUiState: BirthdayUiState) {
        setImageResource(
            when (birthdayUiState) {
                is BirthdayUiState.Fox -> R.drawable.camera_icon_green
                is BirthdayUiState.Pelican -> R.drawable.camera_icon_blue
                is BirthdayUiState.Elephant -> R.drawable.camera_icon_yellow
            }
        )
    }

    @JvmStatic
    @BindingAdapter("uiMode", "android:src", requireAll = true)
    fun CircleImageView.bindChildImageWithPlaceholder(
        birthdayUiState: BirthdayUiState,
        imageUri: String
    ) {
        val placeholder = when (birthdayUiState) {
            BirthdayUiState.Fox -> R.drawable.default_place_holder_green
            BirthdayUiState.Pelican -> R.drawable.default_place_holder_blue
            BirthdayUiState.Elephant -> R.drawable.default_place_holder_yellow
        }

        GlideApp.with(context)
            .load(imageUri)
            .placeholder(placeholder)
            .error(placeholder)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("birth")
    fun ImageView.selectImageForBirth(childLife: ChildLife) {
        val imageId =
            when (childLife.fullYears) {
                0 ->
                    "${childLife.remainingMonths}"
                1 -> {
                    if (childLife.remainingMonths == 6) {
                        "1_half"
                    } else {
                        "${childLife.fullYears}"
                    }
                }
                else ->
                    "${childLife.fullYears}"
            }

        val imgName = "num_$imageId"
        Timber.e("ImageName = $imgName")

        val resourceId = resources.getIdentifier(imgName, "drawable", context.packageName)
        setImageDrawable(ContextCompat.getDrawable(context, resourceId))
    }

    @JvmStatic
    @BindingAdapter("birthStateText")
    fun TextView.bindBirthStatement(childLife: ChildLife) {
        text = String.format(
            context.getString(R.string.title_birthday_statement),
            if (childLife.isBornToday) "${context.getString(R.string.today)} " else "",
            childLife.name
        )
    }

    @JvmStatic
    @BindingAdapter("birthDurationText")
    fun TextView.bindBirthDuration(childLife: ChildLife) {
        @IntegerRes
        val pluralsId: Int
        val number: Int
        when (childLife.fullYears) {
            0 -> {
                pluralsId = R.plurals.months
                number = childLife.remainingMonths
            }
            else -> {
                pluralsId = R.plurals.months
                number = childLife.fullYears
            }
        }

        text = resources.getQuantityString(pluralsId, number)
    }
}