package com.example.workouttrackerandroid

import android.view.View

interface EditButtonClick {
    fun onEditClick(it: View, holder: CustomRecyclerAdapter.ViewHolder, position: Int);
}