package com.example.myfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfragment.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_second.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

    private lateinit var newRecyclerview: RecyclerView
    private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId : Array<String>
    lateinit var heading : Array<String>
    lateinit var news : Array<String>





    private var param1: String? = null
    private var param2: String? = null

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



        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val binding = FragmentFirstBinding.bind(view)



        imageId = arrayOf(
          "*111*120*",
            "111*128*",
            "*111*121*",
            "*111*122*",
            "111*129*",



        )

        heading = arrayOf(
            "Mobi 20",
            "Mobi 30",
            "Mobi 40",
            "Mobi 50",
            "Mobi 60",

        )

        news = arrayOf(

            getString(R.string.yigirma),
            getString(R.string.ottiz),
            getString(R.string.qirq),
            getString(R.string.ellik),
            getString(R.string.oltmish)



        )



        newRecyclerview = binding.recyclerView
        newRecyclerview.layoutManager = LinearLayoutManager(context)
        newRecyclerview.setHasFixedSize(true)

        newArrayList = arrayListOf<News>()

        getUserdata()







        return view
    }

    private fun getUserdata() {

        for (i in imageId.indices){

            val news = News(imageId[i],heading[i])
            newArrayList.add(news)

        }

        var adapter = MyAdapter(newArrayList)
        newRecyclerview.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

//                Toast.makeText(context,"You clicked on item no. $position",Toast.LENGTH_SHORT).show()

//                val intent = Intent(context,SecondFragment::class.java)
//                intent.putExtra("heading",newArrayList[position].heading)
//                intent.putExtra("imageId",newArrayList[position].titleImage)
//                intent.putExtra("news",news[position])
//                startActivity(intent)



                val secondFragment = SecondFragment.newInstance(news[position])

//                findNavController().navigate(R.id.action_firstFragment_to_secondFragment)


                val beginTransaction = parentFragmentManager.beginTransaction()
                beginTransaction.replace(R.id.containerr,secondFragment)
                beginTransaction.addToBackStack(FirstFragment.toString())
                beginTransaction.commit()


            }


        })




    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}