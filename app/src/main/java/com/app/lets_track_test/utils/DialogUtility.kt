package com.app.lets_track_test.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.app.lets_track_test.R

class DialogUtility private constructor() {
    private var progressDialog: AlertDialog? = null

    fun showProgressDialog(context: Context?) {
        if (context != null && (progressDialog == null || !progressDialog!!.isShowing)) {
            val dialogBuilder = AlertDialog.Builder(context)
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView = inflater.inflate(R.layout.progress_dialog, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(false)
            progressDialog = dialogBuilder.create()
            progressDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

            if (progressDialog != null)
                progressDialog!!.show()
        }
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing)
            progressDialog!!.dismiss()
        progressDialog = null
    }

    companion object {

        private var instance: DialogUtility? = null

        fun getInstance(): DialogUtility {

            if (instance == null)
                instance = DialogUtility()

            return instance!!
        }
    }

}
