package thomas.ptlog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.session2.*
import java.util.*

class Session2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session2)

        val genres = arrayOf("Action Movies", "Romantic Movies", "Horror Movies")
        val actionMovies = arrayOf("Dark Knight", "Minority Report", "The Bourne Ultimatum")
        val romanticMovies = arrayOf("50 Shades of Grey", "The Age of Adaline", "Titanic")
        val horrorMovies = arrayOf("Incidious Chapter 3", "It Follows", "We Are Still Here")

        val genresArray = ArrayList<String>()
        val actionMovieArray = ArrayList<String>()
        val romanticMovieArray = ArrayList<String>()
        val horrorMovieArray = ArrayList<String>()
        val childList = HashMap<String, List<String>>()
        genres.forEach { genresArray.add(it) }

        actionMovies.forEach { actionMovieArray.add(it) }
        romanticMovies.forEach { romanticMovieArray.add(it) }
        horrorMovies.forEach { horrorMovieArray.add(it) }


        childList[genresArray[0]] = actionMovieArray
        childList[genresArray[1]] = romanticMovieArray
        childList[genresArray[2]] = horrorMovieArray
        val myAdapter = MyAdapter(genresArray, childList)
        exp_listView.setAdapter(myAdapter)
    }
}

