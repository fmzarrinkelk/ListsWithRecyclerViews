package com.example.listswithrecyclerviews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val LOG_TAG = "PersonListFragment"

class PersonListFragment : Fragment()
{
    private lateinit var personRecyclerView: RecyclerView

    private var adapter: PersonAdapter? = null

    private val myViewModel: MyViewModel by lazy {
        ViewModelProvider(this)[MyViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "Total people: ${myViewModel.people.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_person_list, container, false)

        this.personRecyclerView = view.findViewById(R.id.person_recycler_view) as RecyclerView
        this.personRecyclerView.layoutManager = LinearLayoutManager(context)

        this.updateUI()

        return view
    }

    private fun updateUI()
    {
        val people = myViewModel.people
        adapter = PersonAdapter(people)
        this.personRecyclerView.adapter = adapter
    }

    private inner class PersonHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        private lateinit var person: Person

        private val nameTextView: TextView = this.itemView.findViewById(R.id.list_item_person_name)
        private val ageTextView: TextView = this.itemView.findViewById(R.id.list_item_person_age)
        private val coolCheck: CheckBox = this.itemView.findViewById(R.id.list_item_person_cool)

        fun bind(person: Person)
        {
            this.person = person
            this.nameTextView.text = this.person.name
            this.ageTextView.text = this.person.age.toString()
            this.coolCheck.isChecked = this.person.isSuperCool
        }
    }

    private inner class PersonAdapter (var people: List<Person>)
        : RecyclerView.Adapter<PersonHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder
        {
            val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)
            return PersonHolder(view)
        }

        override fun onBindViewHolder(holder: PersonHolder, position: Int)
        {
            val person = this.people[position]
            holder.bind(person)
        }

        override fun getItemCount() = people.size
    }

    companion object {
        fun newInstance(): PersonListFragment {
            return PersonListFragment()
        }
    }
}

