package ui.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.calculator.bookfinder.header.Header
import com.calculator.bookfinder.header.lancelot
import com.calculator.bookfinder.homepagebooks.BookTitle

import com.calculator.bookfinder.homepagebooks.Books
import com.calculator.bookfinder.homepagebooks.ByAuthor
import com.calculator.bookfinder.naviagtionbar.NaviagtionBar
import com.calculator.bookfinder.ratings.Property1
import com.calculator.bookfinder.ratings.Ratings

import com.google.relay.compose.BoxScopeInstance.columnWeight
import com.google.relay.compose.BoxScopeInstance.rowWeight
import ui.ViewModel.BookViewModel


@Composable
fun HomeScreen(bookViewModel: BookViewModel){
    val list by bookViewModel.homeBookList.collectAsState()
    val ratingList by bookViewModel.ratingList.collectAsState()


    Column (modifier= Modifier
        .fillMaxSize()
        .background(color = Color(0xFFE5DBD0))){
        Header(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp))
        Text(text = "Recomended for you", fontFamily = lancelot, fontSize = 25.sp,       color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ), modifier = Modifier.padding(15.dp))
        if (list.isEmpty()){
            Loading()
            Log.d("ratinglist size 1",(ratingList.size.toString()))
        }
        if (ratingList.size==60){
            Log.d("ratinglist size 1 if",(ratingList.size.toString()))
        }


        LazyColumn(modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.915f),horizontalAlignment = Alignment.CenterHorizontally){
            items(list){book ->

                        Log.d("ratinglist size 2",(ratingList.size.toString()))
                        HomescreenBooks(title = book.title,
                            author = book.authors[0].name ,
                            picId = book.cover_id,
                            modifier = Modifier
                                .rowWeight(1.0f)
                                .columnWeight(1.0f)
                                .height(225.dp)
                                .width(390.dp)
                                .padding(top = 15.dp, bottom = 15.dp),
                            rating = ratingloader(ratingList,list.indexOf(book)) )



                }
            }

        NaviagtionBar(
            homeButton = {},
            searchButton = {},
            savedButton = {},
            property1 = com.calculator.bookfinder.naviagtionbar.Property1.Default,
            modifier = Modifier
                .rowWeight(1.0f)
                .columnWeight(1.0f)
                .fillMaxWidth()
        )

        }

}

fun ratingloader(list : List<Float>,index:Int): Float {
    if(index < list.size){
        return list[index]
    }else{
        return 3f
    }
}


@Composable
fun HomescreenBooks(modifier: Modifier = Modifier, title: String = "", author: String = "", picId: Int,rating: Float){
    com.calculator.bookfinder.homepagebooks.TopLevel(modifier = modifier) {
        Books(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 10.0.dp,
                    y = 11.0.dp
                )
            )
        ) {
            AsyncImage(model ="https://covers.openlibrary.org/b/id/$picId-M.jpg" , contentDescription ="test" ,modifier=Modifier.fillMaxSize())}
        BookTitle(
            title = title,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 133.0.dp,
                    y = 21.0.dp
                )
            )
        )
        ByAuthor(
            author = author,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 137.0.dp,
                    y = 89.0.dp
                )
            )
        )

        Ratings(
            property1 = RatingsDecider(rating),
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 133.0.dp,
                    y = 141.0.dp
                )
            )
        )
    }
}

fun RatingsDecider(rating :Float): Property1 {
    if (rating > 0 && rating < 2f){
        return Property1.Variant5
    }else if(rating > 2 && rating < 3f){
        return Property1.Variant4
    }
    else if(rating > 3 && rating < 4f){
        return Property1.Variant3
    }
    else if(rating > 4 && rating < 4.5f){
        return Property1.Variant2
    }
    else{
        return Property1.Default
    }
}
@Preview
@Composable
fun Loading(){
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){
        CircularProgressIndicator(modifier = Modifier
            .height(120.dp)
            .width(90.dp))
    }

}


