package com.example.gymer

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.gymer.databinding.TrainingsListItemBinding
import com.example.gymer.entity.Training
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class TrainingAdapter(private val trainings: ArrayList<Training>) :
    RecyclerView.Adapter<TrainingAdapter.MyViewHolder>() {
    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
    private lateinit var db: AppDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            TrainingsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        db = AppDatabase.getDatabase(holder.itemView.context)
        with(holder) {
            with(trainings[position]) {
                binding.tvDate.text = date?.let { formatDate(it) }
                binding.tvDescription.text = description
                binding.image.setImageResource(R.drawable.sample_image)

                holder.itemView.setOnClickListener {
                    val dialog: Dialog = Dialog(holder.itemView.context)
                    dialog.setContentView(R.layout.fragment_add_training)
                    dialog.show()

                    val calendarView = dialog.findViewById<CalendarView>(R.id.calendarView)
                    val descEditText = dialog.findViewById<EditText>(R.id.descriptionEditText)
                    val btnUpdate = dialog.findViewById<Button>(R.id.addTrainingBtn)
                    val headerTextView = dialog.findViewById<TextView>(R.id.headerTextView)

                    headerTextView.text = "Edit training"
                    btnUpdate.text = "Update"
                    descEditText.setText(this.description)
                    calendarView.date = this.date!!.time

                    btnUpdate.setOnClickListener {
                        val description = descEditText.text.toString().trim()
                        if (description.isNotEmpty()) {
                            //TODO add photo
                            val training: Training = this
                            training.description = description
                            training.date = Date(calendarView.date)

                            GlobalScope.launch(Dispatchers.IO) {
                                db.trainingDao().update(training)
                                notifyItemChanged(position)
                            }
                            Toast.makeText(
                                holder.itemView.context,
                                "Successfully updated training",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                holder.itemView.context,
                                "Please enter data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog.dismiss()
                    }
                }
                holder.itemView.setOnLongClickListener {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(holder.itemView.context)
                    builder.setCancelable(true)
                    builder.setTitle("Delete selected training?")
                    builder.setIcon(R.drawable.ic_baseline_delete_24)
                    //TODO delete record
                    builder.setPositiveButton("Yes") { dialog, which ->
                        GlobalScope.launch(Dispatchers.IO) {
                            db.trainingDao().delete(trainings[position])
                            trainings.removeAt(position)
                            notifyItemRemoved(position)
                        }
                    }
                    builder.setNegativeButton("No") { dialog, which ->

                    }

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                    true

                }
            }
        }
    }

    private fun formatDate(date: Date): String {
        return simpleDateFormat.format(date)
    }

    override fun getItemCount(): Int {
        return trainings.size
    }

    inner class MyViewHolder(val binding: TrainingsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}