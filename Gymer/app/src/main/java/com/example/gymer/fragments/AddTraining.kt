package com.example.gymer.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gymer.AppDatabase
import com.example.gymer.R
import com.example.gymer.databinding.FragmentAddTrainingBinding
import com.example.gymer.entity.Training
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTraining.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTraining : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddTrainingBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        db = AppDatabase.getDatabase(this.requireContext())
        binding.addTrainingBtn.setOnClickListener{
            insertTrainingWithExercises()
        }
        binding.calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val calender: Calendar = Calendar.getInstance()
            calender.set(year, month, dayOfMonth)
            calendarView.setDate(calender.timeInMillis, true, true)
        }
        binding.calendarView.date = System.currentTimeMillis()

        binding.addExerciseButton.setOnClickListener{
            addNewExerciseInputs()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun insertTrainingWithExercises() {
        val date = Date(binding.calendarView.date)
        val description = binding.descriptionEditText.text.toString().trim()
        if (description.isNotEmpty()) {
            //TODO add photo
            val training = Training(null, date, description, null)

            GlobalScope.launch(Dispatchers.IO) {
                db.trainingDao().insert(training)
            }
            binding.descriptionEditText.text.clear()
            Toast.makeText(this.requireContext(), "Successfully added new training", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this.requireContext(), "Please enter data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addNewExerciseInputs(){
        val view = layoutInflater.inflate(R.layout.add_exercise_inputs, null)
        val spinner = view.findViewById<Spinner>(R.id.bodyPartSpinner)
        binding.exercisesLinearLayout.addView(view)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddTraining.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTraining().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}